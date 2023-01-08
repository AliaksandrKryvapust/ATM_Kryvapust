package aliaksandrkryvapust.atm.service.api;

import java.math.BigDecimal;

public interface IService<TYPE> {
    BigDecimal checkBalance(String cardNumber) throws Exception;
    void deposit(TYPE user, BigDecimal amount) throws Exception;
    void withdraw(TYPE user, BigDecimal amount) throws Exception;
}
