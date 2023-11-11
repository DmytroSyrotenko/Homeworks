package dao.impls;

import dao.StudentDao;
import entity.Student;
import factory.JdbcFactory;
import paginationdata.PaginationData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    private static final String CREATE_STUDENT = "insert into students values (default, ?, ?, ?)";
    private static final String UPDATE_STUDENT = "update students set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_STUDENT = "delete from students where id = ?";
    private static final String FIND_STUDENT = "select * from students where id = ";

    private static final String FIND_ALL_STUDENTS_BY_DEFAULT = "select * from students limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_ID_ASC = "select * from students order by id limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_FIRST_NAME_ASC = "select * from students order by first_name limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_LAST_NAME_ASC = "select * from students order by last_name limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_AGE_ASC = "select * from students order by age limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_ID_DESC = "select * from students order by id desc limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_FIRST_NAME_DESC = "select * from students order by first_name desc limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_LAST_NAME_DESC = "select * from students order by last_name desc limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_AGE_DESC = "select * from students order by age desc limit ?, ?";

    private static final String EXISTS_BY_ID = "select count(id) as count_of_students from students where id = ";
    private static final String FIND_ALL_STUDENTS_BY_GROUP = "select id, first_name, last_name, age from " +
            "students as s left join group_student as gs on s.id = gs.student_id where gs.group_id = ?";

    @Override
    public void create(Student student) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CREATE_STUDENT)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(UPDATE_STUDENT)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setLong(4, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_STUDENT)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (ResultSet rs = jdbcFactory.getStatement().executeQuery(FIND_STUDENT + id)) {
            rs.next();
            return Optional.of(generateStudentByResultSet(rs));
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll(PaginationData data) {
        int start = (data.getPage() - 1) * data.getSize();
        Collection<Student> students = new ArrayList<>();
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(defineNecessaryPreparedStatement(data))) {
            ps.setInt(1, start);
            ps.setInt(2, data.getSize());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(generateStudentByResultSet(rs));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String defineNecessaryPreparedStatement(PaginationData data) {
        String necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_DEFAULT;
        if (data.getSort().equalsIgnoreCase("asc")) {
            if (data.getOrderBy().equalsIgnoreCase("id")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_ID_ASC;
            } else if (data.getOrderBy().equalsIgnoreCase("first_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_FIRST_NAME_ASC;
            } else if (data.getOrderBy().equalsIgnoreCase("last_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_LAST_NAME_ASC;
            } else if (data.getOrderBy().equalsIgnoreCase("age")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_AGE_ASC;
            }
        } else {
            if (data.getOrderBy().equalsIgnoreCase("id")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_ID_DESC;
            } else if (data.getOrderBy().equalsIgnoreCase("first_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_FIRST_NAME_DESC;
            } else if (data.getOrderBy().equalsIgnoreCase("last_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_LAST_NAME_DESC;
            } else if (data.getOrderBy().equalsIgnoreCase("age")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_AGE_DESC;
            }
        }
        return necessaryPreparedStatement;
    }

    @Override
    public boolean existsById(Long id) {
        try (ResultSet rs = jdbcFactory.getStatement().executeQuery(EXISTS_BY_ID + id)) {
            rs.next();
            long count = rs.getLong("count_of_students");
            return count == 1;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return false;
    }

    private Student generateStudentByResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        Long id = rs.getLong("id");
        String fn = rs.getString("first_name");
        String ln = rs.getString("last_name");
        int age = rs.getInt("age");
        student.setId(id);
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        return student;
    }

    public Collection<Student> findAllStudentsByGroup(Long groupId) {
        Collection<Student> students = new ArrayList<>();
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(FIND_ALL_STUDENTS_BY_GROUP)) {
            ps.setLong(1, groupId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(generateStudentByResultSet(rs));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
