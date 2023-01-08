package aliaksandrkryvapust.atm.service;

import aliaksandrkryvapust.atm.dao.AccountDao;
import aliaksandrkryvapust.atm.dao.api.IAccountDao;
import aliaksandrkryvapust.atm.dao.entity.AccountRecord;
import aliaksandrkryvapust.atm.dao.entity.User;
import aliaksandrkryvapust.atm.exeption.LimitExceededException;
import aliaksandrkryvapust.atm.service.api.IAtmService;

import java.math.BigDecimal;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.NOT_ENOUGH_MONEY_ACCOUNT;
import static aliaksandrkryvapust.atm.core.ParamsAndConstants.NOT_ENOUGH_MONEY_ATM;
import static java.lang.String.format;

public class AtmService implements IAtmService {
    private BigDecimal availableAmount;
    private final IAccountDao accountDAO;

    public AtmService(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
        this.accountDAO = new AccountDao();
    }

    @Override
    public BigDecimal checkBalance(String cardNumber) throws Exception {
        return accountDAO.readLastAccountRecord(cardNumber).getBalance();
    }

    @Override
    public void deposit(User user, BigDecimal amount) throws Exception {
        AccountRecord lastAccountRecord = accountDAO.readLastAccountRecord(user.getCardNumber());
        AccountRecord accountRecord = createAccountRecord(lastAccountRecord, lastAccountRecord.getBalance().add(amount));
        accountDAO.writeAccountRecord(accountRecord);
        availableAmount = availableAmount.add(amount);
    }

    @Override
    public void withdraw(User user, BigDecimal amount) throws Exception {
        if (availableAmount.compareTo(amount) < 0) {
            throw new LimitExceededException(format(NOT_ENOUGH_MONEY_ATM, availableAmount));
        }
        AccountRecord lastAccountRecord = accountDAO.readLastAccountRecord(user.getCardNumber());
        BigDecimal actualBalance = lastAccountRecord.getBalance();
        if (actualBalance.compareTo(amount) < 0) {
            throw new LimitExceededException(NOT_ENOUGH_MONEY_ACCOUNT);
        }
        AccountRecord accountRecord = createAccountRecord(lastAccountRecord, actualBalance.subtract(amount));
        accountDAO.writeAccountRecord(accountRecord);
        availableAmount = availableAmount.subtract(amount);
    }

    private AccountRecord createAccountRecord(AccountRecord lastAccountRecord, BigDecimal balance) {
        return AccountRecord.builder()
                .cardNumber(lastAccountRecord.getCardNumber())
                .pin(lastAccountRecord.getPin())
                .balance(balance)
                .isLocked(lastAccountRecord.isLocked())
                .dateOfLocked(lastAccountRecord.getDateOfLocked())
                .failedAttempt(lastAccountRecord.getFailedAttempt())
                .build();
    }
}
