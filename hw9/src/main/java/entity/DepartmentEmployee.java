package entity;

public class DepartmentEmployee {

    String departmentId;
    String employeeId;

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "departmentId='" + departmentId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
