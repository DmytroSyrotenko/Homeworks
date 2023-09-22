package qq.module1.Impl;

import qq.module1.db.Db;
import qq.module1.entity.GroupStudent;

public class GroupStudentCrudServiceImpl {
    Db db = new Db();

    public void createGroupStudent(GroupStudent groupStudent) {
        db.createGroupStudent(groupStudent);
    }


}
