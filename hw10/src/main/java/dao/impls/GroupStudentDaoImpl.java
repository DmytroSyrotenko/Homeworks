package dao.impls;

import factory.JdbcFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupStudentDaoImpl {

    final JdbcFactory jdbcFactory = JdbcFactory.getInstance();

    private static final String ADD_STUDENT_TO_GROUP = "insert into group_student values (?, ?)";
    private static final String DELETE_STUDENT_FROM_GROUP = "delete from group_student where student_id = ? and group_id = ?";
    private static final String DELETE_ALL_FOR_GROUP = "delete from group_student where group_id = ?";
    private static final String DELETE_ALL_FOR_STUDENT = "delete from group_student where student_id = ?";


    public void addStudentToGroup(Long studentId, Long groupId) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(ADD_STUDENT_TO_GROUP)) {
            ps.setLong(1, studentId);
            ps.setLong(2, groupId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteStudentFromGroup(Long studentId, Long groupId) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_STUDENT_FROM_GROUP)) {
            ps.setLong(1, studentId);
            ps.setLong(2, groupId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAllForGroup(Long groupId){
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_ALL_FOR_GROUP)) {
            ps.setLong(1, groupId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAllForStudent(Long studentId){
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_ALL_FOR_STUDENT)) {
            ps.setLong(1, studentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
