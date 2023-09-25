package java8.module1.service.impl;

import java8.module1.service.StudentCrudService;
import java8.module1.db.Db;
import java8.module1.entity.Student;

public class StudentCrudServiceImpl implements StudentCrudService {


    Db db = Db.getInstance();

    @Override
    public void create(Student student) {
        db.createStudent(student);
        System.out.println("Student created");
    }

    @Override
    public void update(Student student) {
        if (db.isPresentS(student.getId())) {
            db.updateStudent(student);
            System.out.println("Student updated");
        }
    }

    @Override
    public void delete(String id) {
        if (db.isPresentS(id)) {
            db.deleteStudent(id);
            System.out.println("Student deleted");
            db.deleteStudentFromAllGroups(id);
            System.out.println("also deleted from all groups");
        }


    }

    @Override
    public Student findOne(String id) {
        if (db.isPresentS(id)) {
            return db.findOneStudent(id);
        }
        return new Student();
    }


    @Override
    public Student[] findAll() {
        return db.findAllStudents();
    }
}
