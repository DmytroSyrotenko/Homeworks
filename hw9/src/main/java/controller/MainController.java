package controller;

import myReader.MyReader;
import entity.Department;
import entity.DepartmentEmployee;
import entity.Employee;
import service.DepartmentCrudService;
import service.EmployeeCrudService;
import service.impls.DepartmentCrudServiceImpl;
import service.impls.DepartmentEmployeeService;
import service.impls.EmployeeCrudServiceImpl;

import java.util.Collection;

public class MainController {

    private final EmployeeCrudService employeeCrudService = new EmployeeCrudServiceImpl();
    private final DepartmentCrudService departmentCrudService = new DepartmentCrudServiceImpl();
    private final DepartmentEmployeeService departmentEmployeeService = new DepartmentEmployeeService();

    public void start() {
        MyReader myReader = new MyReader();
        menu();
        String position;
        while ((position = myReader.readLine()) != null) {
            crud(position, myReader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Create employee enter 1");
        System.out.println("Find employee enter 2");
        System.out.println("Delete employee enter 3");
        System.out.println("Update employee enter 4");
        System.out.println("Find all employees enter 5");
        System.out.println("---------");
        System.out.println("Create department enter 6");
        System.out.println("Find department enter 7");
        System.out.println("Delete department enter 8");
        System.out.println("Update department enter 9");
        System.out.println("Find all departments enter 10");
        System.out.println("---------");
        System.out.println("Add employee to department enter 11");
        System.out.println("Delete employee from department enter 12");
        System.out.println("Find find all departments for employee enter 13");
        System.out.println("Find find all employees for department enter 14");
        System.out.println("Find all connections(department-employee) enter 15");
        System.out.println("---------");
        System.out.println("Close app enter 0");
    }

    private void crud(String position, MyReader myReader) {
        switch (position) {
            case "1" -> createEmployee(myReader);
            case "2" -> findOneEmployee(myReader);
            case "3" -> deleteEmployee(myReader);
            case "4" -> updateEmployee(myReader);
            case "5" -> findAllEmployees();

            case "6" -> createDepartment(myReader);
            case "7" -> findOneDepartment(myReader);
            case "8" -> deleteDepartment(myReader);
            case "9" -> updateDepartment(myReader);
            case "10" -> findAllDepartments();

            case "11" -> addEmployeeToDepartment(myReader);
            case "12" -> deleteEmployeeFromDepartment(myReader);
            case "13" -> findAllDepartmentsForEmployee(myReader);
            case "14" -> findAllEmployeesForDepartment(myReader);
            case "15" -> findAllDepartmentsEmployees();

            case "0" -> System.exit(0);
        }
    }

    private void createEmployee(MyReader reader) {
        System.out.println("enter first name");
        String fn = reader.readLine();
        System.out.println("enter last name");
        String ln = reader.readLine();
        System.out.println("enter age");
        int age;
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Employee employee = new Employee().setFirstName(fn).setLastName(ln);
        employee.setFirstName(fn);
        employee.setLastName(ln);
        employee.setAge(age);
        employeeCrudService.create(employee);
    }

    private void updateEmployee(MyReader reader) {
        System.out.println("enter id");
        String id = reader.readLine();
        System.out.println("enter first name");
        String fn = reader.readLine();
        System.out.println("enter last name");
        String ln = reader.readLine();
        System.out.println("enter age");
        int age;
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(fn);
        employee.setLastName(ln);
        employee.setAge(age);
        employeeCrudService.update(employee);
    }

    private void findOneEmployee(MyReader reader) {
        System.out.println("enter id");
        String id = reader.readLine();
        Employee employee = employeeCrudService.findOne(id);
        System.out.println("id = " + employee.getId());
        System.out.println("first name = " + employee.getFirstName());
        System.out.println("last name = " + employee.getLastName());
        System.out.println("age = " + employee.getAge());
    }

    private void deleteEmployee(MyReader reader) {
        System.out.println("enter employee id");
        String id = reader.readLine();
        employeeCrudService.delete(id);
    }

    private void findAllEmployees() {
        Collection<Employee> employees = employeeCrudService.findAll();
        employees.forEach(employee -> {
            System.out.println("--------");
            System.out.println("id = " + employee.getId());
            System.out.println("first name = " + employee.getFirstName());
            System.out.println("last name = " + employee.getLastName());
            System.out.println("age = " + employee.getAge());
        });
    }


    private void createDepartment(MyReader reader) {
        System.out.println("enter department name");
        String dn = reader.readLine();
        System.out.println("enter personal quantity");
        int quantity;
        try {
            quantity = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Department department = new Department();
        department.setDeptName(dn);
        department.setPersonalQuantity(quantity);
        departmentCrudService.create(department);
    }


    private void updateDepartment(MyReader reader) {
        System.out.println("enter id");
        String id = reader.readLine();
        System.out.println("enter new department name");
        String dn = reader.readLine();
        System.out.println("enter new personal quantity in department");
        int quantity;
        try {
            quantity = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Department department = new Department();
        department.setId(id);
        department.setDeptName(dn);
        department.setPersonalQuantity(quantity);
        departmentCrudService.update(department);
    }

    private void findOneDepartment(MyReader reader) {
        System.out.println("enter id");
        String id = reader.readLine();
        Department department = departmentCrudService.findOne(id);
        System.out.println("id = " + department.getId());
        System.out.println("department name = " + department.getDeptName());
        System.out.println("department quantity = " + department.getPersonalQuantity());
    }

    private void deleteDepartment(MyReader reader) {
        System.out.println("enter department id");
        String id = reader.readLine();
        departmentCrudService.delete(id);
    }

    private void findAllDepartments() {
        Collection<Department> departments = departmentCrudService.findAll();
        departments.forEach(department -> {
            System.out.println("--------");
            System.out.println("id = " + department.getId());
            System.out.println("department name = " + department.getDeptName());
            System.out.println("department quantity = " + department.getPersonalQuantity());
        });
    }

    private void addEmployeeToDepartment(MyReader reader) {
        System.out.println("enter employee id");
        String employeeId = reader.readLine();
        System.out.println("enter department id");
        String departmentId = reader.readLine();
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setDepartmentId(departmentId);
        departmentEmployee.setEmployeeId(employeeId);
        departmentEmployeeService.create(departmentEmployee);

    }

    private void findAllDepartmentsEmployees() {
        Collection<DepartmentEmployee> departmentEmployees = departmentEmployeeService.findAll();
        Collection<Department> departments = departmentCrudService.findAll();
        Collection<Employee> employees = employeeCrudService.findAll();

        departmentEmployees.forEach(o ->
        {
            System.out.println("--------");
            System.out.println("Employee Id:" + o.getEmployeeId());
            employees.stream()
                    .filter(e -> (e.getId().equals(o.getEmployeeId())))
                    .forEach(e -> {
                        System.out.println("first name = " + e.getFirstName());
                        System.out.println("last name = " + e.getLastName());
                        System.out.println("age = " + e.getAge());
                    });
            System.out.println("Department Id:" + o.getDepartmentId());
            departments.stream()
                    .filter(d -> (d.getId().equals(o.getDepartmentId())))
                    .forEach(d -> {
                        System.out.println("department name = " + d.getDeptName());
                        System.out.println("department quantity = " + d.getPersonalQuantity());
                    });
        });
    }

    private void deleteEmployeeFromDepartment(MyReader reader) {
        System.out.println("enter employee id");
        String empId = reader.readLine();
        System.out.println("enter department id");
        String deptId = reader.readLine();
        departmentEmployeeService.deleteEmployeeFromDepartment(empId, deptId);
    }


    private void findAllDepartmentsForEmployee(MyReader reader) {
        System.out.println("enter employee id");
        String empId = reader.readLine();
        Employee employee = employeeCrudService.findOne(empId);
        Collection<DepartmentEmployee> departmentEmployees = departmentEmployeeService.findAll();

        System.out.println("--------");
        System.out.println("Employee Id:" + employee.getId());
        departmentEmployees.stream()
                .filter(de -> de.getEmployeeId().equals(empId))
                .forEach(de -> System.out.println("Department Id:" + de.getDepartmentId()));
    }

    private void findAllEmployeesForDepartment(MyReader reader) {
        System.out.println("enter department id");
        String depId = reader.readLine();
        Department department = departmentCrudService.findOne(depId);
        Collection<DepartmentEmployee> departmentEmployees = departmentEmployeeService.findAll();

        System.out.println("--------");
        System.out.println("Department Id:" + department.getId());
        departmentEmployees.stream()
                .filter(de -> de.getDepartmentId().equals(depId))
                .forEach(de -> System.out.println("Employee Id:" + de.getEmployeeId()));
    }
}
