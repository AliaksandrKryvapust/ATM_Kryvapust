package aliaksandrkryvapust.atm.service;

import aliaksandrkryvapust.atm.controller.validator.InputDataValidator;
import aliaksandrkryvapust.atm.controller.validator.api.IInputDataValidator;
import aliaksandrkryvapust.atm.dao.entity.User;
import aliaksandrkryvapust.atm.dao.entity.UserChoice;
import aliaksandrkryvapust.atm.service.api.IInputDataService;

import java.math.BigDecimal;
import java.util.Scanner;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.*;

public class InputDataService implements IInputDataService {

    private final Scanner scanner;
    private final IInputDataValidator inputDataValidator;

    public InputDataService() {
        this.scanner = new Scanner(System.in);
        this.inputDataValidator = new InputDataValidator();
    }

    public User getUserInfo() {
        String cardNumber = enterAndValidateCardNumber();
        String pin = enterPin();
        return new User(cardNumber, pin);
    }

    public UserChoice makeUserChoice() {
        System.out.println("What would you like to do? Please choose number of option");
        System.out.print(UserChoice.getAllOptionInfo());
        int answer = 0;
        boolean isValidData = false;
        do {
            if (!scanner.hasNextInt()) {
                System.out.println(INCORRECT_OPTION_CHOICE_MESSAGE);
                scanner.nextLine();
            } else {
                answer = scanner.nextInt();
                scanner.nextLine();
                isValidData = true;
            }
        } while (!isValidData);
        return UserChoice.getByPosition(answer);
    }

    public BigDecimal inputAmount() {
        BigDecimal amount = null;
        boolean isValidData = false;
        do {
            System.out.print(INPUT_AMOUNT);
            if (!scanner.hasNextInt()) {
                System.out.println(INCORRECT_NUMBER_MESSAGE);
                scanner.nextLine();
            } else {
                int value = scanner.nextInt();
                scanner.nextLine();
                amount = new BigDecimal(value);
                isValidData = inputDataValidator.isPositiveAmount(amount);
                if (!isValidData) {
                    System.out.println(INCORRECT_NUMBER_MESSAGE);
                }
            }
        } while (!isValidData);
        return amount;
    }

    public BigDecimal inputDepositAmount() {
        BigDecimal amount;
        boolean isValidData;
        do {
            amount = inputAmount();
            isValidData = inputDataValidator.isValidDepositAmount(amount);
            if (!isValidData) {
                System.out.printf(DEPOSIT_RESTRICTION_MESSAGE, RESTRICTION_FOR_DEPOSIT);
            }
        } while (!isValidData);
        return amount;
    }

    private String enterAndValidateCardNumber() {
        System.out.print(ENTER_CARD_NUMBER);
        String cardNumber = null;
        boolean isValidCardNumber = false;
        do {
            if (!scanner.hasNext()) {
                System.out.println(INCORRECT_CARD_NUMBER_MESSAGE);
                scanner.nextLine();
            } else {
                cardNumber = scanner.nextLine();
                isValidCardNumber = inputDataValidator.validateCardNumber(cardNumber);
                if (!isValidCardNumber) {
                    System.out.println(INCORRECT_CARD_NUMBER_MESSAGE);
                }
            }
        } while (!isValidCardNumber);
        return cardNumber;
    }

    private String enterPin() {
        System.out.print(ENTER_PIN);
        return scanner.nextLine();
    }
}
