package aliaksandrkryvapust.atm.controller.validator;

import aliaksandrkryvapust.atm.controller.validator.api.IInputDataValidator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.RESTRICTION_FOR_DEPOSIT;

public class InputDataValidator implements IInputDataValidator {

    @Override
    public boolean validateCardNumber(String cardNumber) {
        return Pattern.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}", cardNumber);
    }

    @Override
    public boolean isValidDepositAmount(BigDecimal amount) {
        return amount.compareTo(RESTRICTION_FOR_DEPOSIT) < 1;
    }

    @Override
    public boolean isPositiveAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
