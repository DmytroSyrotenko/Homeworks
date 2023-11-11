package service.impls;

import dao.GroupDao;
import dao.StudentDao;
import dao.impls.GroupDaoImpl;
import dao.impls.GroupStudentDaoImpl;
import dao.impls.StudentDaoImpl;
import entity.Student;
import java.util.Collection;

public class GroupStudentServiceImpl {

    final GroupStudentDaoImpl groupStudentDao = new GroupStudentDaoImpl();
    final StudentDao studentDao = new StudentDaoImpl();
    final GroupDao groupCrudDao = new GroupDaoImpl();

    public void addStudentToGroup(Long studentId, Long groupId) {
        if (!studentDao.existsById(studentId) || !groupCrudDao.existsById(groupId)) {
            throw new RuntimeException("Entered student or group ID not exist.Check");
        }
        groupStudentDao.addStudentToGroup(studentId, groupId);
    }

    public Collection<Student> findAllStudentsByGroup(Long groupId) {
        return studentDao.findAllStudentsByGroup(groupId);
    }

    public void deleteStudentFromGroup(Long studentId, Long groupId) {
        groupStudentDao.deleteStudentFromGroup(studentId, groupId);
    }
}
