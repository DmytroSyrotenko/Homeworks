package service.impls;

import dao.CrudDao;
import dao.impl.CsvDepartmentEmployeeDao;
import dao.impl.CsvEmployeeDao;
import entity.Employee;
import service.EmployeeCrudService;
import java.util.Collection;
import java.util.Optional;

public class EmployeeCrudServiceImpl implements EmployeeCrudService {
    CrudDao<Employee> implDao =CsvEmployeeDao.getInstance();
    CsvDepartmentEmployeeDao deptImplDao = CsvDepartmentEmployeeDao.getInstance();

    @Override
    public void create(Employee employee) {
        implDao.create(employee);
        System.out.println("Employee created");
    }


    @Override
    public void update(Employee employee) {
        if (!implDao.existsById(employee.getId())) {
            System.out.println("Employee not found");
            return;
        }
        implDao.update(employee);
        System.out.println("Employee updated");
    }

    @Override
    public void delete(String id) {
        if (!implDao.existsById(id)) {
            System.out.println("Employee not found");
            return;
        }
        deptImplDao.deleteAllAboutGroupOrEmployee(id);
        implDao.delete(id);
        System.out.println("Employee deleted");
    }


    @Override
    public Employee findOne(String id) {
        Optional<Employee> optionalEmployee = implDao.findById(id);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found.Try again");
        }
        return optionalEmployee.get();
    }

    @Override
    public Collection<Employee> findAll() {
        return implDao.findAll();
    }
}
