package aliaksandrkryvapust.atm.dao.entity;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.POSITION_CHOICE_NOT_FOUND;
import static java.lang.String.format;

public enum UserChoice {
    CHECK_BALANCE(1, "Check balance."),
    WITHDRAW(2, "Withdraw money."),
    DEPOSIT(3, "Deposit funds."),
    EXIT(4, "Exit.");
    private final int position;
    private final String description;

    UserChoice(int position, String description) {
        this.position = position;
        this.description = description;
    }

    public static String getAllOptionInfo() {
        StringBuilder result = new StringBuilder();
        for (UserChoice element : UserChoice.values()) {
            result.append(format("%d - %s \n", element.position, element.description));
        }
        return result.toString();
    }

    public static UserChoice getByPosition(int position) {
        for (UserChoice element : UserChoice.values()) {
            if (element.position == position) {
                return element;
            }
        }
        throw new IllegalArgumentException(POSITION_CHOICE_NOT_FOUND);
    }
}


