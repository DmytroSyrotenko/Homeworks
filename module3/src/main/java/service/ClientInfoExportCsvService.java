package service;

import java.util.Date;

public interface ClientInfoExportCsvService {


    void exportCsv(Long accountId, Date from, Date until);
}
