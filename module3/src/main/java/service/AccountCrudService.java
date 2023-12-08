package service;

import entity.Account;
import entity.Transaction;


public interface AccountCrudService extends CrudService<Account> {

    void attachTransactionToSenderAccountAndChangeSum(Transaction transaction);
}
