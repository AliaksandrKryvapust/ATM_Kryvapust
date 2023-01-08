package aliaksandrkryvapust.atm.controller;

import aliaksandrkryvapust.atm.controller.api.IAtmController;
import aliaksandrkryvapust.atm.dao.entity.User;
import aliaksandrkryvapust.atm.dao.entity.UserChoice;
import aliaksandrkryvapust.atm.service.AtmService;
import aliaksandrkryvapust.atm.service.InputDataService;
import aliaksandrkryvapust.atm.service.SecurityService;
import aliaksandrkryvapust.atm.service.api.IAtmService;
import aliaksandrkryvapust.atm.service.api.IInputDataService;
import aliaksandrkryvapust.atm.service.api.ISecurityService;

import java.math.BigDecimal;

import static aliaksandrkryvapust.atm.core.ParamsAndConstants.*;

public class AtmController implements IAtmController {
    private final IAtmService atmService;
    private final ISecurityService securityService;
    private final IInputDataService inputDataService;

    public AtmController() {
        this.inputDataService = new InputDataService();
        this.atmService = new AtmService(INITIAL_AMOUNT_IN_ATM);
        this.securityService = new SecurityService();
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println(WELCOME);
            User user = inputDataService.getUserInfo();
            try {
                securityService.login(user);
                boolean isActiveSession = true;
                do {
                    UserChoice userChoice = inputDataService.makeUserChoice();
                    switch (userChoice) {
                        case CHECK_BALANCE:
                            BigDecimal balance = atmService.checkBalance(user.getCardNumber());
                            System.out.printf(BALANCE_MESSAGE, balance);
                            break;
                        case WITHDRAW:
                            BigDecimal withdrawAmount = inputDataService.inputAmount();
                            atmService.withdraw(user, withdrawAmount);
                            System.out.println(WITHDRAW_MESSAGE);
                            break;
                        case DEPOSIT:
                            BigDecimal depositAmount = inputDataService.inputDepositAmount();
                            atmService.deposit(user, depositAmount);
                            System.out.println(DEPOSIT_MESSAGE);
                            break;
                        case EXIT:
                            isActiveSession = false;
                            break;
                        default:
                            System.out.printf(LOGIN_NOT_IMPLEMENTED_MESSAGE, userChoice.name());
                    }
                } while (isActiveSession);
            } catch (Exception e) {
                System.out.printf(WARNING, e.getMessage());
            }
        }
    }
}
