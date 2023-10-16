package service.impls;

import dao.CrudDao;
import dao.impl.CsvDepartmentDao;
import dao.impl.CsvDepartmentEmployeeDao;
import dao.impl.CsvEmployeeDao;
import entity.Department;
import entity.DepartmentEmployee;
import entity.Employee;

import java.util.Collection;

public class DepartmentEmployeeService {

    CsvDepartmentEmployeeDao deptImplDao = new CsvDepartmentEmployeeDao();
    CrudDao<Employee> implDao = CsvEmployeeDao.getInstance();
    CrudDao<Department> deptDao = CsvDepartmentDao.getInstance();

    public void create(DepartmentEmployee departmentEmployee) {
        if (implDao.existsById(departmentEmployee.getEmployeeId()) && deptDao.existsById(departmentEmployee.getDepartmentId()) && !deptImplDao.isDepartmentEmployeeExists(departmentEmployee.getEmployeeId(), departmentEmployee.getDepartmentId())) {
            deptImplDao.addEmployeeToDepartment(departmentEmployee);
            System.out.println("Employee added to group");
        } else {
            System.out.println("Employee or group not found OR such employee already connected to this department.Check info");
        }
    }

    public Collection<DepartmentEmployee> findAll() {
        return deptImplDao.findAll();
    }

    public void deleteEmployeeFromDepartment(String employeeId, String departmentId) {
        if (deptImplDao.isDepartmentEmployeeExists(employeeId, departmentId)) {
            deptImplDao.deleteEmployeeFromDepartment(employeeId, departmentId);
            System.out.println("Employee deleted from department");
        }
        else{
            System.out.println("Such department+employee not found");
        }
    }
}
