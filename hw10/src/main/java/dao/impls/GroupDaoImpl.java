package dao.impls;

import dao.GroupDao;
import entity.Group;
import factory.JdbcFactory;
import paginationdata.PaginationData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class GroupDaoImpl implements GroupDao {

    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    private static final String CREATE_GROUP = "insert into groupss values (default, ?, ?)";
    private static final String UPDATE_GROUP = "update groupss set group_name = ?, teacher = ? where id = ?";
    private static final String DELETE_GROUP = "delete from groupss where id = ?";
    private static final String FIND_GROUP = "select * from groupss where id = ";

    private static final String FIND_ALL_STUDENTS_BY_DEFAULT = "select * from groupss limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_ID_ASC = "select * from groupss order by id limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_GROUP_NAME_ASC = "select * from groupss order by group_name limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_TEACHER_ASC = "select * from groupss order by teacher limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_ID_DESC = "select * from groupss order by id desc limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_GROUP_NAME_DESC = "select * from groupss order by group_name desc limit ?, ?";
    private static final String FIND_ALL_STUDENTS_BY_TEACHER_DESC = "select * from groupss order by teacher desc limit ?, ?";



    private static final String EXISTS_BY_ID = "select count(id) as count_of_groups from groupss where id = ";

    @Override
    public void create(Group group) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CREATE_GROUP)) {
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getTeacher());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Group group) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(UPDATE_GROUP)) {
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getTeacher());
            ps.setLong(3, group.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_GROUP)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Group> findById(Long id) {
        try (ResultSet rs = jdbcFactory.getStatement().executeQuery(FIND_GROUP + id)) {
            rs.next();
            return Optional.of(generateGroupByResultSet(rs));
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Collection<Group> findAll(PaginationData data) {
        int start = (data.getPage() - 1) * data.getSize();
        Collection<Group> groups = new ArrayList<>();
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(defineNecessaryPreparedStatement(data))) {
            ps.setInt(1, start);
            ps.setInt(2, data.getSize());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                groups.add(generateGroupByResultSet(rs));
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String defineNecessaryPreparedStatement(PaginationData data) {
        String necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_DEFAULT;
        if (data.getSort().equalsIgnoreCase("asc")) {
            if (data.getOrderBy().equalsIgnoreCase("id")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_ID_ASC;
            } else if (data.getOrderBy().equalsIgnoreCase("group_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_GROUP_NAME_ASC;
            } else if (data.getOrderBy().equalsIgnoreCase("teacher")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_TEACHER_ASC;
            }
        } else {
            if (data.getOrderBy().equalsIgnoreCase("id")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_ID_DESC;
            } else if (data.getOrderBy().equalsIgnoreCase("group_name")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_GROUP_NAME_DESC;
            } else if (data.getOrderBy().equalsIgnoreCase("teacher")) {
                necessaryPreparedStatement = FIND_ALL_STUDENTS_BY_TEACHER_DESC;
            }
        }
        return necessaryPreparedStatement;
    }

    @Override
    public boolean existsById(Long id) {
        try (ResultSet rs = jdbcFactory.getStatement().executeQuery(EXISTS_BY_ID + id)) {
            rs.next();
            long count = rs.getLong("count_of_groups");
            return count == 1;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return false;
    }

    private Group generateGroupByResultSet(ResultSet rs) throws SQLException {
        Group group = new Group();
        Long id = rs.getLong("id");
        String gn = rs.getString("group_name");
        String gt = rs.getString("teacher");
        group.setId(id);
        group.setGroupName(gn);
        group.setTeacher(gt);
        return group;
    }
}
