package service.impl;

import dao.ExportInCsvDao;
import dao.ExtractWithJdbcDao;
import dao.csvexport.ExportInCsvDaoImpl;
import dao.jdbcImpl.ExtractWithJdbcDaoImpl;
import entity.Transaction;
import service.ClientInfoExportCsvService;
import java.util.Collection;
import java.util.Date;

public class ClientInfoExportCsvServiceImpl implements ClientInfoExportCsvService {

    final ExtractWithJdbcDao extractWithJdbcDao = new ExtractWithJdbcDaoImpl();
    final ExportInCsvDao exportInCsvDao = new ExportInCsvDaoImpl();

    @Override
    public void exportCsv(Long accountId, Date from, Date until) {
        Collection<Transaction> collection = extractWithJdbcDao.importFromDatabaseJdbc(accountId, from, until );
        if (collection.isEmpty()) {
            System.out.println("There is no information available for the requested period");
            return;
        }
        exportInCsvDao.export(collection);
        System.out.println("Data transferred in csv file");
    }
}
