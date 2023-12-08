package dao.jdbcImpl;

import dao.ExtractWithJdbcDao;
import entity.Transaction;
import jbdc.factory.JdbcFactory;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class ExtractWithJdbcDaoImpl implements ExtractWithJdbcDao {

    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    private static final String FIND_TRANSACTIONS_BY_DATE = "SELECT * FROM transactions_table where transaction_time between ? and ? and account_id = ?";

    @Override
    public Collection<Transaction> importFromDatabaseJdbc(Long accountId, Date from, Date until) {
        Collection<Transaction> transactionCollection = new ArrayList<>();
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(FIND_TRANSACTIONS_BY_DATE)) {
            ps.setDate(1, (java.sql.Date) from);
            ps.setDate(2, (java.sql.Date) until);
            ps.setLong(3, accountId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                transactionCollection.add(generateTransactionByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(transactionCollection);
        return transactionCollection;
    }


    private Transaction generateTransactionByResultSet(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        Long id = rs.getLong("id");
        Long senderId = rs.getLong("sender_id");
        Long recipientId = rs.getLong("recipient_id");
        BigDecimal sum = BigDecimal.valueOf(rs.getDouble("sum"));
        String transactionType = rs.getString("transaction_type");
        Date transactionTime = rs.getTimestamp("transaction_time");
        String transactionComment = rs.getString("transaction_comment");
        transaction.setId(id);
        transaction.setSender(senderId);
        transaction.setRecipient(recipientId);
        transaction.setSum(sum);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionTime(transactionTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        transaction.setComment(transactionComment);
        return transaction;
    }
}
