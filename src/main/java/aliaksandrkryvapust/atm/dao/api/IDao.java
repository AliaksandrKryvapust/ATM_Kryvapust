package aliaksandrkryvapust.atm.dao.api;

import aliaksandrkryvapust.atm.exeption.FailedAccessToFileDataException;

public interface IDao<TYPE> {
    TYPE readLastAccountRecord(String cardNumber) throws Exception;
    void writeAccountRecord(TYPE accountRecord) throws FailedAccessToFileDataException;
}
