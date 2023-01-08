package aliaksandrkryvapust.atm.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.DELIMITER;
import static aliaksandrkryvapust.atm.core.ParamsAndConstants.EMPTY_VALUE;
import static java.util.Objects.isNull;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class AccountRecord {
    private final String cardNumber;
    private final String pin;
    private final BigDecimal balance;
    private final boolean isLocked;
    private final LocalDateTime dateOfLocked;
    private final int failedAttempt;

    public String getInfoForRecording() {
        return cardNumber +
                DELIMITER +
                pin +
                DELIMITER +
                balance.toString() +
                DELIMITER +
                isLocked +
                DELIMITER +
                (isNull(dateOfLocked) ? EMPTY_VALUE : dateOfLocked) +
                DELIMITER +
                failedAttempt;
    }
}
