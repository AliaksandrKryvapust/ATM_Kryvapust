package aliaksandrkryvapust.atm.service.api;

import aliaksandrkryvapust.atm.dao.entity.User;
import aliaksandrkryvapust.atm.dao.entity.UserChoice;
import aliaksandrkryvapust.atm.exeption.IncorrectInputException;

import java.math.BigDecimal;

public interface IInputDataService {
    User getUserInfo();

    UserChoice makeUserChoice();

    BigDecimal inputDepositAmount() throws IncorrectInputException;

    BigDecimal inputAmount();
}
