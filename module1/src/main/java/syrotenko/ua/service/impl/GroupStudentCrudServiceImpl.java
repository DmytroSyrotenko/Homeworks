package syrotenko.ua.service.impl;


import syrotenko.ua.db.Db;
import syrotenko.ua.entity.GroupStudent;

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

