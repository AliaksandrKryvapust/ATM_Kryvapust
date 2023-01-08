package aliaksandrkryvapust.atm.dao;

import aliaksandrkryvapust.atm.dao.api.IAccountDao;
import aliaksandrkryvapust.atm.dao.entity.AccountRecord;
import aliaksandrkryvapust.atm.exeption.FailedAccessToFileDataException;
import aliaksandrkryvapust.atm.exeption.UserNotExistException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.*;
import static java.lang.Boolean.parseBoolean;
import static java.util.Objects.isNull;

public class AccountDao implements IAccountDao {

    @Override
    public AccountRecord readLastAccountRecord(String cardNumber) throws Exception {
        AccountRecord accountRecord;
        File file = getFileData();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            String[] accountRecordParts = null;
            while ((line = reader.readLine()) != null) {
                accountRecordParts = getRecordParts(cardNumber, line, accountRecordParts);
            }
            if (isNull(accountRecordParts)) {
                throw new UserNotExistException(USER_DOESNT_EXIST_MESSAGE);
            } else {
                accountRecord = createAccountRecord(accountRecordParts);
            }
        } catch (Exception e) {
            throw new Exception(FAILED_ACCESS_TO_FILE_MESSAGE);
        }
        return accountRecord;
    }

    @Override
    public void writeAccountRecord(AccountRecord accountRecord) throws FailedAccessToFileDataException {
        File file = getFileData();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), StandardOpenOption.APPEND)) {
            bufferedWriter.write(accountRecord.getInfoForRecording().toCharArray());
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new FailedAccessToFileDataException(FAILED_ACCESS_TO_FILE_MESSAGE + "(Operation not finished)");
        }
    }

    private File getFileData() {
        String path = "./" + FILE_NAME_DATA;
        return new File(path);
    }

    private AccountRecord createAccountRecord(String[] accountRecordParts) throws Exception {
        if (isNull(accountRecordParts)) {
            return null;
        } else {
            try {
                String cardNumber = accountRecordParts[0];
                String pin = accountRecordParts[1];
                BigDecimal balance = new BigDecimal(accountRecordParts[2]);
                boolean isLocked = parseBoolean(accountRecordParts[3]);
                LocalDateTime dateOfLocked = accountRecordParts[4].equals(EMPTY_VALUE) ? null : LocalDateTime.parse(accountRecordParts[4]);
                int failedAttempt = Integer.parseInt(accountRecordParts[5]);
                return new AccountRecord(cardNumber, pin, balance, isLocked, dateOfLocked, failedAttempt);
            } catch (RuntimeException exception) {
                throw new Exception(exception);
            }
        }
    }

    private String[] getRecordParts(String cardNumber, String line, String[] accountRecordParts) {
        String[] parts = line.split(DELIMITER);
        String accountNumber = parts[0];
        if (accountNumber.equals(cardNumber)) {
            accountRecordParts = parts;
        }
        return accountRecordParts;
    }
}
