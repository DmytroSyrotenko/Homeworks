package syrotenko.ua.service.impl;


import syrotenko.ua.db.Db;
import syrotenko.ua.entity.GroupStudent;

public class GroupStudentCrudServiceImpl {
    Db db = Db.getInstance();

    public void createGroupStudent(GroupStudent groupStudent) {
        if (db.isPresentGroup(groupStudent.getGroupId()) && db.isPresentStudent(groupStudent.getStudentId())&& !db.isPresentGroupStudent(groupStudent.getGroupId(),groupStudent.getStudentId())) {
            db.createGroupStudent(groupStudent);
            System.out.println("Student added to group");
        }else {
            System.out.println("Such pair already exists");
        }
    }


    public void deleteGroupStudent(String studentId, String groupId) {
        db.deleteStudentFromGroup(studentId, groupId);
        System.out.println("Student deleted from group");
    }

    public GroupStudent[] findAllGroupsAndStudents() {
        return db.findAllGroupsAndStudents();
    }

}

