package dao.csvexport;

import au.com.bytecode.opencsv.CSVWriter;
import dao.ExportInCsvDao;
import entity.Transaction;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class ExportInCsvDaoImpl implements ExportInCsvDao {

    @Override
    public void export(Collection<Transaction> collection) {
        Class<Transaction> transactionClass = Transaction.class;
        Field[] fields = transactionClass.getDeclaredFields();
        List<String[]> listOfFields = new ArrayList<>();
        String[] string = new String[]{"transactionId-"};
        for (Field field : fields) {
            string[0] = string[0].concat(field.getName()).concat("-");
        }
        listOfFields.add(string);

        List<Transaction> transactionList = new ArrayList<>(collection);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("data_export.csv"))) {
            List<String[]> list = new ArrayList<>();
            for (Transaction transaction : transactionList) {
                String[] strings = new String[]{
                        String.valueOf(transaction.getId()),
                        transaction.getTransactionType(),
                        String.valueOf(transaction.getSender()),
                        String.valueOf(transaction.getRecipient()),
                        String.valueOf(transaction.getSum()),
                        transaction.getComment(),
                        String.valueOf(transaction.getTransactionTime()).substring(0, 19)
                };
                list.add(strings);
            }
            csvWriter.writeAll(listOfFields);
            csvWriter.writeAll(list);
            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }
}