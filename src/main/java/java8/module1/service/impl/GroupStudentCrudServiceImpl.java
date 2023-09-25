package java8.module1.service.impl;

import java8.module1.db.Db;
import java8.module1.entity.GroupStudent;
import java8.module1.entity.Student;
import java8.module1.service.StudentCrudService;


public class GroupStudentCrudServiceImpl {
    Db db = Db.getInstance();

    public void createGroupStudent(GroupStudent groupStudent) {
        db.createGroupStudent(groupStudent);
        System.out.println("Student added to group");
    }


    public void deleteGroupStudent(String studentId,String groupId) {
        db.deleteStudentFromGroup(studentId,groupId);
        System.out.println("Student deleted from all groups");
    }

    public GroupStudent[] findAllGroupsAndStudents() {
        return db.findAllGroupsAndStudents();
    }

}

