package dao;

import entity.Transaction;
import java.util.Collection;
import java.util.Date;

public interface ExtractWithJdbcDao {

    Collection<Transaction> importFromDatabaseJdbc(Long accountId, Date from, Date until);
}
