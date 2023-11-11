package service.impls;

import dao.CrudDao;
import dao.impls.GroupStudentDaoImpl;
import dao.impls.StudentDaoImpl;
import entity.Student;
import paginationdata.PaginationData;
import service.StudentCrudService;
import java.util.Collection;
import java.util.Optional;

public class StudentCrudServiceImpl implements StudentCrudService {

    private final CrudDao<Student> crudDaoStudent = new StudentDaoImpl();
    private final GroupStudentDaoImpl groupStudentDao = new GroupStudentDaoImpl();

    @Override
    public void create(Student student) {
        crudDaoStudent.create(student);
    }

    @Override
    public void update(Student student) {
        if (!crudDaoStudent.existsById(student.getId())) {
            throw new RuntimeException("Student not found");
        }
        crudDaoStudent.update(student);
    }

    @Override
    public void delete(Long id) {
        if (!crudDaoStudent.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        groupStudentDao.deleteAllForStudent(id);
        crudDaoStudent.delete(id);
    }

    @Override
    public Student findOne(Long id) {
        Optional<Student> optionalStudent = crudDaoStudent.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        return optionalStudent.get();
    }

    @Override
    public Collection<Student> findAll(PaginationData data) {
        if (data.getPage()==0||data.getSize()==0){
            throw new RuntimeException("Page value should be > 0");
        }
        return crudDaoStudent.findAll(data);
    }
}