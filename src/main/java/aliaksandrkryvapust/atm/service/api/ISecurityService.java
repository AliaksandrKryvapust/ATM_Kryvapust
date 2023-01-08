package aliaksandrkryvapust.atm.service.api;

import aliaksandrkryvapust.atm.dao.entity.User;

public interface ISecurityService {
    void login(User user) throws Exception;
}
