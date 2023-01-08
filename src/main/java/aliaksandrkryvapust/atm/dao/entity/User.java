package aliaksandrkryvapust.atm.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final String cardNumber;
    private final String pin;
}
