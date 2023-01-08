package aliaksandrkryvapust.atm.controller.validator.api;

import java.math.BigDecimal;

public interface IInputDataValidator {
    boolean validateCardNumber(String cardNumber);
    boolean isValidDepositAmount(BigDecimal amount);
    boolean isPositiveAmount(BigDecimal amount);
}
