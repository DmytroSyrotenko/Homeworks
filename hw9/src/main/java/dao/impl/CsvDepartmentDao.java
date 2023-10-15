package dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import dao.DepartmentDao;
import entity.Department;
import util.DbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvDepartmentDao implements DepartmentDao {

    private List<Department> departments = new ArrayList<>();

    /* ------instance------------*/
    private static CsvDepartmentDao instance;

    private CsvDepartmentDao() {
    }

    public static CsvDepartmentDao getInstance() {
        if (instance != null) {
            return instance;
        } else {
            var csvDepartmentDao = new CsvDepartmentDao();
            instance = csvDepartmentDao;
            return csvDepartmentDao;
        }
    }
    /* ------instance------------*/

    @Override
    public void create(Department department) {
        readCsv();
        department.setId(DbUtil.generateId(departments));
        departments.add(department);
        writeCsv();
    }

    @Override
    public void update(Department department) {
        readCsv();
        for (Department d : departments) {
            if (d.getId().equals(department.getId())) {
                departments.set(departments.indexOf(d), department);
            }
        }
        writeCsv();
    }

    @Override
    public void delete(String id) {
        readCsv();
        departments.removeIf(e -> e.getId().equals(id));
        writeCsv();
    }

    @Override
    public Optional<Department> findById(String id) {
        readCsv();
        return departments.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Department> findAll() {
        readCsv();
        return this.departments;
    }

    @Override
    public boolean existsById(String id) {
        readCsv();
        return departments.stream().anyMatch(e -> e.getId().equals(id));
    }

    private void readCsv() {
        try (CSVReader csvReader = new CSVReader(new FileReader("departments.csv"))) {
            List<String[]> departmentList = csvReader.readAll();
            departments = new ArrayList<>();
            for (String[] strings : departmentList) {
                Department department = new Department();
                department.setId(strings[0]);
                department.setDeptName(strings[1]);
                department.setPersonalQuantity(Integer.parseInt(strings[2]));
                departments.add(department);

            }
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }

    private void writeCsv() {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("departments.csv"))) {
            List<String[]> departmentList = new ArrayList<>();
            for (Department department : departments) {
                String[] strings = new String[]{
                        department.getId(),
                        department.getDeptName(),
                        String.valueOf(department.getPersonalQuantity())
                };
                departmentList.add(strings);
            }
            csvWriter.writeAll(departmentList);
            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("exception= " + e.getMessage());
        }
    }
}
