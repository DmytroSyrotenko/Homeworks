package syrotenko.ua.service.impl;


import syrotenko.ua.db.Db;
import syrotenko.ua.entity.Group;
import syrotenko.ua.service.GroupCrudService;

public class GroupCrudServiceImpl implements GroupCrudService {
    Db db = Db.getInstance();

    @Override
    public void create(Group group) {
       db.createGroup(group);
       System.out.println("Group created");
    }

    @Override
    public void update(Group group) {
        if (db.isPresentG(group.getId())) {
            db.updateGroup(group);
            System.out.println("Group updated");
        }
    }

    @Override
    public void delete(String id) {
        if (db.isPresentG(id)) {
            db.deleteGroup(id);
            System.out.println("Group deleted");
        }
    }

    @Override
    public Group findOne(String id) {
        if ((db.isPresentG(id))) {
            return db.findOneGroup(id);
        }
        return new Group();
    }

    @Override
    public Group[] findAll() {
        return db.findAllGroups();
    }
}
