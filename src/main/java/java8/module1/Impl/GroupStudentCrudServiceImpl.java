package java8.module1.Impl;

import java8.module1.db.Db;
import java8.module1.entity.GroupStudent;

public class GroupStudentCrudServiceImpl {
    Db db = new Db();

    public void createGroupStudent(GroupStudent groupStudent) {
        db.createGroupStudent(groupStudent);
    }


}
