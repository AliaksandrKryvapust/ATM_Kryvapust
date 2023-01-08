package aliaksandrkryvapust.atm.controller.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class InputDataValidatorTest {

    @InjectMocks
    private InputDataValidator inputDataValidator;

    @Test
    void validateCardNumber() {
        // prerequisites
        String correctValue = "1235-4568-4456-1123";
        String inCorrectValue1 = "1235-4568-456-1123";
        String inCorrectValue2 = "1235-ssd-4456-1123";
        String inCorrectValue3 = "12356000-4456-1123";

        // test
        boolean actualCorrect = inputDataValidator.validateCardNumber(correctValue);
        boolean actualInCorrect1 = inputDataValidator.validateCardNumber(inCorrectValue1);
        boolean actualInCorrect2 = inputDataValidator.validateCardNumber(inCorrectValue2);
        boolean actualInCorrect3 = inputDataValidator.validateCardNumber(inCorrectValue3);

        // assert
        assertTrue(actualCorrect);
        assertFalse(actualInCorrect1);
        assertFalse(actualInCorrect2);
        assertFalse(actualInCorrect3);
    }

    @Test
    void isValidDepositAmount() {
        // prerequisites
        BigDecimal correctValue1 = new BigDecimal("875");
        BigDecimal correctValue2 = new BigDecimal("100000");
        BigDecimal inCorrectValue = new BigDecimal("1000001");

        // test
        boolean actualCorrect1 = inputDataValidator.isValidDepositAmount(correctValue1);
        boolean actualCorrect2 = inputDataValidator.isValidDepositAmount(correctValue2);
        boolean actualInCorrect = inputDataValidator.isValidDepositAmount(inCorrectValue);

        // assert
        assertTrue(actualCorrect1);
        assertTrue(actualCorrect2);
        assertFalse(actualInCorrect);
    }

    @Test
    void isPositiveAmount() {
        // prerequisites
        BigDecimal correctValue = new BigDecimal("999");
        BigDecimal inCorrectValue = new BigDecimal("-1000");

        // test
        boolean actualCorrect = inputDataValidator.isPositiveAmount(correctValue);
        boolean actualInCorrect = inputDataValidator.isPositiveAmount(inCorrectValue);

        // assert
        assertTrue(actualCorrect);
        assertFalse(actualInCorrect);
    }
}