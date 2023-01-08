package aliaksandrkryvapust.atm.core;

import java.math.BigDecimal;

public class ParamsAndConstants {

    /**
     * Setting variable for ATM work
     */
    public static final String FILE_NAME_DATA = "data_account.txt";
    public static final String DELIMITER = " ";
    public static final String EMPTY_VALUE = "-";
    public static final int COUNT_LOGIN_ATTEMPT = 3;
    public static final BigDecimal INITIAL_AMOUNT_IN_ATM = new BigDecimal("1000000");
    public static final BigDecimal RESTRICTION_FOR_DEPOSIT = new BigDecimal("1000000");

    /**
     * Warning information messages.
     */
    public static final String WARNING = "Warning: %s \n";
    public static final String LOGIN_NOT_IMPLEMENTED_MESSAGE = "Warning: Logic for operation %s isn't fully implement. Choose another.\n";
    public static final String POSITION_CHOICE_NOT_FOUND = "Incorrect position number. Please, choice from the list.";
    public static final String FAILED_ACCESS_TO_FILE_MESSAGE = "Warning: inner error while working with file data. Please try again or contact to support.";
    public static final String USER_DOESNT_EXIST_MESSAGE = "User with card number: doesn't exist in the system. Please contact to your Bank.";
    public static final String NOT_ENOUGH_MONEY_ACCOUNT = "Operation is not allowed. Check you balance.";
    public static final String NOT_ENOUGH_MONEY_ATM = "ATM (from Aliaksandr Kryvapust) don't have enough money. Available amount: %s";
    public static final String INCORRECT_NUMBER_MESSAGE = "Warning: Your input has incorrect data type. Must be integer positive value. Please, try again.";
    public static final String DEPOSIT_RESTRICTION_MESSAGE = "Warning: You can't deposit more than %s. Please, try again.\n";
    public static final String INCORRECT_OPTION_CHOICE_MESSAGE = "Warning: Your input data incorrect. Must be only numbers. Please, try again.";
    public static final String INCORRECT_CARD_NUMBER_MESSAGE = "Warning: Your card number isn't valid. Please, try again.";
    public static final String CARD_NUMBER_NOT_FOUND = "Your cardNumber not found in the system.";
    public static final String ACCOUNT_BLOCKED = "Your account is blocked. Try later.";
    public static final String PIN_INCORRECT = "Your pin isn't correct. You have %d attempts. Try input pin again.";
    public static final String ACCOUNT_WAS_LOCKED = "Your pin isn't correct. Your account is locked for 1 day.";

    /**
     * Instruction message.
     */
    public static final String WELCOME = " -- Welcome to the implementation of ATM from Aliaksandr Kryvapust -- ";
    public static final String BALANCE_MESSAGE = "Your balance: %s\n";
    public static final String WITHDRAW_MESSAGE = "Withdraw operation finished. Take your money.";
    public static final String DEPOSIT_MESSAGE = "Deposit operation finished.";
    public static final String ENTER_CARD_NUMBER = "Enter your card number (Input must be \"XXXX-XXXX-XXXX-XXXX\"): ";
    public static final String ENTER_PIN = "Enter your PIN: ";
    public static final String INPUT_AMOUNT = "Please, input amount: ";
}
