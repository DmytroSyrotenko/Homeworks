package dao;

import entity.Transaction;
import java.util.Collection;

public interface ExportInCsvDao {

    void export(Collection<Transaction> collection);
}
