package entity;

public class Department extends BaseEntity {

    String deptName;
    int personalQuantity;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getPersonalQuantity() {
        return personalQuantity;
    }

    public void setPersonalQuantity(int personalQuantity) {
        this.personalQuantity = personalQuantity;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptName='" + deptName + '\'' +
                ", personalQuantity=" + personalQuantity +
                '}';
    }
}
