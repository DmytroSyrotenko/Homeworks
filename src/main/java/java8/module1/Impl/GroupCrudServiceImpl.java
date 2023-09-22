package java8.module1.Impl;

import java8.module1.Impl.interfaces.GroupCrudService;
import java8.module1.db.Db;
import java8.module1.entity.Group;

public class GroupCrudServiceImpl implements GroupCrudService {
    Db db = new Db();

    @Override
    public void create(Group group) {
        db.createGroup(group);
    }

    @Override
    public void update(Group group) {
        db.updateGroup(group);
    }

    @Override
    public void delete(String id) {
        db.deleteGroup(id);
    }

    @Override
    public Group findOne(String id) {
        return db.findOneGroup(id);
    }

    @Override
    public Group[] findAll() {
        return db.findAllGroups();
    }
}
