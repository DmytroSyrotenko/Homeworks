package dao;

import entity.Student;
import java.util.Collection;

public interface StudentDao extends CrudDao<Student> {

    Collection<Student> findAllStudentsByGroup(Long groupId);
}
