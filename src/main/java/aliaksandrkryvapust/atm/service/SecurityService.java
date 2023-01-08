package aliaksandrkryvapust.atm.service;

import aliaksandrkryvapust.atm.dao.AccountDao;
import aliaksandrkryvapust.atm.dao.api.IAccountDao;
import aliaksandrkryvapust.atm.dao.entity.AccountRecord;
import aliaksandrkryvapust.atm.dao.entity.User;
import aliaksandrkryvapust.atm.exeption.BlockedUserException;
import aliaksandrkryvapust.atm.exeption.FailedAccessToFileDataException;
import aliaksandrkryvapust.atm.exeption.UserNotExistException;
import aliaksandrkryvapust.atm.service.api.ISecurityService;

import java.time.LocalDateTime;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;

public class SecurityService implements ISecurityService {
    private final IAccountDao accountDAO;

    public SecurityService() {
        this.accountDAO = new AccountDao();
    }

    public void login(User user) throws Exception {
        AccountRecord lastAccountRecord = accountDAO.readLastAccountRecord(user.getCardNumber());
        if (isNull(lastAccountRecord)) {
            throw new UserNotExistException(CARD_NUMBER_NOT_FOUND);
        }
        if (lastAccountRecord.isLocked()) {
            if (lastAccountRecord.getDateOfLocked().isAfter(LocalDateTime.now().minusDays(1L))) {
                throw new BlockedUserException(ACCOUNT_BLOCKED);
            } else {
                unlockAccount(lastAccountRecord);
            }
        }
        if (!lastAccountRecord.getPin().equals(user.getPin())) {
            handleFailedAttempt(lastAccountRecord);
        }
    }

    private void handleFailedAttempt(AccountRecord accountRecord) throws FailedAccessToFileDataException, BlockedUserException {
        if (accountRecord.getFailedAttempt() < COUNT_LOGIN_ATTEMPT - 1) {
            AccountRecord savedAccountRecord = new AccountRecord(accountRecord.getCardNumber(),
                    accountRecord.getPin(), accountRecord.getBalance(),
                    false, null, accountRecord.getFailedAttempt() + 1);
            accountDAO.writeAccountRecord(savedAccountRecord);
            throw new BlockedUserException(format(PIN_INCORRECT, COUNT_LOGIN_ATTEMPT - savedAccountRecord.getFailedAttempt()));
        } else {
            accountDAO.writeAccountRecord(new AccountRecord(accountRecord.getCardNumber(),
                    accountRecord.getPin(), accountRecord.getBalance(),
                    true, LocalDateTime.now(), COUNT_LOGIN_ATTEMPT));
            throw new BlockedUserException(ACCOUNT_WAS_LOCKED);
        }

    }

    private void unlockAccount(AccountRecord accountRecord) throws FailedAccessToFileDataException {
        accountDAO.writeAccountRecord(new AccountRecord(accountRecord.getCardNumber(),
                accountRecord.getPin(), accountRecord.getBalance(),
                false, null, 0));
    }
}
