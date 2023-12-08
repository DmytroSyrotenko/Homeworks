package service.impl;

import dao.TransactionCrudDao;
import dao.impl.TransactionCrudDaoImpl;
import entity.Transaction;
import service.TransactionCrudService;
import java.util.Collection;
import java.util.Optional;

public class TransactionCrudServiceImpl implements TransactionCrudService {

    private final TransactionCrudDao transactionCrudDao = new TransactionCrudDaoImpl();

    @Override
    public void create(Transaction transaction) {
        transactionCrudDao.create(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        transactionCrudDao.update(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionCrudDao.delete(id);
    }

    @Override
    public Transaction findOne(Long id) {
        Optional<Transaction> optionalTransaction = transactionCrudDao.findById(id);
        if (optionalTransaction.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        return optionalTransaction.get();
    }

    @Override
    public Collection<Transaction> findAll() {
        return null;
    }
}
