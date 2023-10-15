package dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import dao.EmployeeDao;
import entity.Employee;
import util.DbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class CsvEmployeeDao implements EmployeeDao {

    private List<Employee> employees = new ArrayList<>();


    /* ------instance------------*/
    private static CsvEmployeeDao instance;
    private CsvEmployeeDao() {}
    public static CsvEmployeeDao getInstance() {
        if (instance != null) {
            return instance;
        } else {
            var csvEmployeeDao = new CsvEmployeeDao();
            instance = csvEmployeeDao;
            return csvEmployeeDao;
        }
    }
    /* ------instance------------*/


    @Override
    public void create(Employee employee) {
        readCsv();
        employee.setId(DbUtil.generateId(employees));
        employees.add(employee);
        writeCsv();
    }

    @Override
    public void update(Employee employee) {
        readCsv();
        for (Employee e : employees) {
            if (e.getId().equals(employee.getId())){
                employees.set(employees.indexOf(e),employee);
            }
        }
        writeCsv();
    }

    @Override
    public void delete(String id) {
        readCsv();
        employees.removeIf(e -> e.getId().equals(id));
        writeCsv();
    }

    @Override
    public Optional<Employee> findById(String id) {
        readCsv();
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Employee> findAll() {
        readCsv();
        return this.employees;
    }

    @Override
    public boolean existsById(String id) {
        readCsv();
        return employees.stream().anyMatch(e -> e.getId().equals(id));
    }

    private void readCsv() {
        try (CSVReader csvReader = new CSVReader(new FileReader("employees.csv"))) {
            List<String[]> employeeList = csvReader.readAll();
            employees = new ArrayList<>();
            for (String[] strings : employeeList) {
                Employee employee = new Employee();
                employee.setId(strings[0]);
                employee.setFirstName(strings[1]);
                employee.setLastName(strings[2]);
                employee.setAge(Integer.parseInt(strings[3]));
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }

    private void writeCsv() {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("employees.csv"))) {
            List<String[]> employeeList = new ArrayList<>();
            for (Employee employee : employees) {
                String[] strings = new String[]{
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        String.valueOf(employee.getAge())
                };
                employeeList.add(strings);
            }
            csvWriter.writeAll(employeeList);
            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }
}
