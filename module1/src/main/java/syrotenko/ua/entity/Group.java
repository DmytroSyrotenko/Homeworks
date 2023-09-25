package syrotenko.ua.entity;

public class Group extends BaseEntity {

    private String name;
    private String teacher;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
