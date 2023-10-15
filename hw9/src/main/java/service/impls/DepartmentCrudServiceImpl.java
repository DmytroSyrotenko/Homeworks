package service.impls;

import dao.CrudDao;
import dao.impl.CsvDepartmentDao;
import dao.impl.CsvDepartmentEmployeeDao;
import entity.Department;
import service.DepartmentCrudService;

import java.util.Collection;
import java.util.Optional;

public class DepartmentCrudServiceImpl implements DepartmentCrudService {
    CrudDao<Department> deptDao = CsvDepartmentDao.getInstance();
    CsvDepartmentEmployeeDao deptImplDao = CsvDepartmentEmployeeDao.getInstance();

    @Override
    public void create(Department department) {
        deptDao.create(department);
        System.out.println("Department created");
    }

    @Override
    public void update(Department department) {
        if (!deptDao.existsById(department.getId())) {
            System.out.println("Department not found.Try actual id");
            return;
        }
        deptDao.update(department);
        System.out.println("Department updated");
}

    @Override
    public void delete(String id) {
        if (!deptDao.existsById(id)) {
            System.out.println("Department not found.Try actual id");
            return;
        }
        deptImplDao.deleteAllAboutGroupOrEmployee(id);
        deptDao.delete(id);
        System.out.println("Department deleted");
    }

    @Override
    public Department findOne(String id) {
        Optional<Department> optionalDepartment = deptDao.findById(id);

        if (optionalDepartment.isEmpty()) {
            throw new RuntimeException("Department not found.Try again");
        }
        return optionalDepartment.get();
    }

    @Override
    public Collection<Department> findAll() {
        return deptDao.findAll();
    }
}
