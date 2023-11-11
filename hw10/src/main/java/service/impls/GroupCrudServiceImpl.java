package service.impls;

import dao.CrudDao;
import dao.impls.GroupDaoImpl;
import dao.impls.GroupStudentDaoImpl;
import entity.Group;
import paginationdata.PaginationData;
import service.GroupCrudService;
import java.util.Collection;
import java.util.Optional;

public class GroupCrudServiceImpl implements GroupCrudService {
    private final CrudDao<Group> crudDaoGroup = new GroupDaoImpl();
    private final GroupStudentDaoImpl groupStudentDao = new GroupStudentDaoImpl();

    @Override
    public void create(Group group) {
        crudDaoGroup.create(group);
    }

    @Override
    public void update(Group group) {
        if (!crudDaoGroup.existsById(group.getId())) {
            throw new RuntimeException("Group not found");
        }
        crudDaoGroup.update(group);
    }

    @Override
    public void delete(Long id) {
        if (!crudDaoGroup.existsById(id)) {
            throw new RuntimeException("Group not found");
        }
        groupStudentDao.deleteAllForGroup(id);
        crudDaoGroup.delete(id);
    }

    @Override
    public Group findOne(Long id) {
        Optional<Group> optionalGroup = crudDaoGroup.findById(id);
        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        return optionalGroup.get();
    }

    @Override
    public Collection<Group> findAll(PaginationData data) {
        if (data.getPage()==0||data.getSize()==0){
            throw new RuntimeException("Page value should be > 0");
        }
        return crudDaoGroup.findAll(data);
    }
}
