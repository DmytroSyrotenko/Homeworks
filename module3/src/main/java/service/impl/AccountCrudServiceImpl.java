package service.impl;

import dao.AccountCrudDao;
import dao.impl.AccountCrudDaoImpl;
import entity.Account;
import entity.Transaction;
import service.AccountCrudService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class AccountCrudServiceImpl implements AccountCrudService {

    final AccountCrudDao accountCrudDao = new AccountCrudDaoImpl();


    @Override
    public void create(Account account) {
        accountCrudDao.create(account);
    }

    @Override
    public void update(Account account) {
        accountCrudDao.update(account);
    }

    @Override
    public void delete(Long id) {
        accountCrudDao.delete(id);
    }

    @Override
    public Account findOne(Long id) {
        Optional<Account> optionalAccount = accountCrudDao.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        return optionalAccount.get();
    }

    @Override
    public Collection<Account> findAll() {
        return accountCrudDao.findAll();
    }

    @Override
    public void attachTransactionToSenderAccountAndChangeSum(Transaction transaction) {
        if (isExist(transaction.getSender())
                && isExist(transaction.getRecipient())
                && balanceIsOk(transaction)
        &&isPaymentMoreThanZero(transaction.getSum())
        ) {
            transaction.setTransactionType("PAYMENT");
            Account account = findOne(transaction.getSender());
            BigDecimal subtractedSum = account.getSum().subtract(transaction.getSum());
            account.setSum(subtractedSum);
            account.getTransactions().add(transaction);
            update(account);
            attachTransactionToRecipientAccountAndChangeSum(transaction);
        }
    }

    private boolean isExist(Long accountId) {
        return findOne(accountId) != null;
    }

    private boolean balanceIsOk(Transaction transaction) {
        if (findOne(transaction.getSender()).getSum().compareTo(transaction.getSum()) < 0) {
            throw new RuntimeException("Not enough money to make transaction");
        }
        return true;
    }

    private boolean isPaymentMoreThanZero(BigDecimal sumOfPayment) {
        if (sumOfPayment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Payment should be more than zero");
        }
        return true;
    }

    private void attachTransactionToRecipientAccountAndChangeSum(Transaction transaction) {
        Account account = findOne(transaction.getRecipient());
        BigDecimal addedSum = account.getSum().add(transaction.getSum());
        account.setSum(addedSum);
        transaction.setTransactionType("RECEIVING");
        account.getTransactions().add(transaction);
        update(account);
    }

}