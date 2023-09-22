package qq.module1.entity;

public class GroupStudent {

    private String groupId;
    private String studentId;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "GroupStudent{" +
                "groupId='" + groupId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
