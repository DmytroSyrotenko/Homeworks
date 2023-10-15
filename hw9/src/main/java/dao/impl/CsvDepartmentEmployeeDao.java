package dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import entity.DepartmentEmployee;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvDepartmentEmployeeDao {
    private List<DepartmentEmployee> departmentEmployees = new ArrayList<>();
    private static CsvDepartmentEmployeeDao instance;

    public CsvDepartmentEmployeeDao() {
    }

    public static CsvDepartmentEmployeeDao getInstance() {
        if (instance != null) {
            return instance;
        } else {
            var csvDepartmentEmployeeDao = new CsvDepartmentEmployeeDao();
            instance = csvDepartmentEmployeeDao;
            return csvDepartmentEmployeeDao;
        }
    }
    public void addEmployeeToDepartment(DepartmentEmployee departmentEmployee) {
        readCsv();
        departmentEmployees.add(departmentEmployee);
        writeCsv();
    }

    public void deleteEmployeeFromDepartment(String employeeId, String departmentId) {
        readCsv();
        departmentEmployees.removeIf(dE -> dE.getEmployeeId().equals(employeeId) && dE.getDepartmentId().equals(departmentId));
        writeCsv();
    }

    public void deleteAllAboutGroupOrEmployee(String employeeOrDepartmentId) {
        readCsv();
        departmentEmployees.removeIf(dE -> dE.getEmployeeId().equals(employeeOrDepartmentId) || dE.getDepartmentId().equals(employeeOrDepartmentId));
        writeCsv();
    }

    public boolean isDepartmentEmployeeExists(String employeeId, String departmentId) {
        readCsv();
        for (DepartmentEmployee dE : departmentEmployees) {
            if (dE.getEmployeeId().equals(employeeId) && dE.getDepartmentId().equals(departmentId)) {
                return true;
            }
        }
        return false;
    }

    public Collection<DepartmentEmployee> findAll() {
        readCsv();
        return this.departmentEmployees;
    }

    private void readCsv() {
        try (CSVReader csvReader = new CSVReader(new FileReader("departmentEmployee.csv"))) {
            List<String[]> departmentEmployeeList = csvReader.readAll();
            departmentEmployees = new ArrayList<>();
            for (String[] strings : departmentEmployeeList) {
                DepartmentEmployee departmentEmployee = new DepartmentEmployee();

                departmentEmployee.setDepartmentId(strings[0]);
                departmentEmployee.setEmployeeId(strings[1]);

                departmentEmployees.add(departmentEmployee);
            }
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }

    private void writeCsv() {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("departmentEmployee.csv"))) {
            List<String[]> departmentEmployeeList = new ArrayList<>();
            for (DepartmentEmployee departmentEmployee : departmentEmployees) {
                String[] strings = new String[]{
                        departmentEmployee.getDepartmentId(),
                        departmentEmployee.getEmployeeId()
                };
                departmentEmployeeList.add(strings);
            }
            csvWriter.writeAll(departmentEmployeeList);
            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }
}
