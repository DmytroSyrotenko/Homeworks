package qq.module1.db;

import qq.module1.entity.Group;
import qq.module1.entity.GroupStudent;
import qq.module1.entity.Student;

import java.util.Objects;
import java.util.UUID;

public class Db {

    private Student[] students = new Student[2];
    private Group[] groups = new Group[2];
    private GroupStudent[] groupStudents = new GroupStudent[2];
    private int lastStudentIndex = 0;
    private int lastGroupIndex = 0;
    private int lastGroupStudentIndex = 0;

    ///////student
    public void createStudent(Student student) {
        if (lastStudentIndex == students.length - 1) {
            Student[] newStudents = new Student[students.length * 2];
            System.arraycopy(students, 0, newStudents, 0, students.length);
            students = newStudents;
            addStudent(student);
        } else {
            addStudent(student);
        }

    }

    public Student[] findAllStudents() {
        return students;
    }


    private void addStudent(Student student) {
        student.setId(UUID.randomUUID().toString());
        students[lastStudentIndex] = student;
        lastStudentIndex++;
    }

    public void deleteStudent(String id) {

        int index = 0;

        for (int i = 0; i < students.length; i++) {
            if (Objects.equals(students[i].getId(), id)) {
                index = i;
                Student[] newStudents = new Student[students.length - 1];
                System.arraycopy(students, 0, newStudents, 0, index);
                System.arraycopy(students, index + 1, newStudents, index, students.length - index - 1);
                students = newStudents;
                lastStudentIndex--;
                System.out.println("Student with id " + id + " deleted");
                break;
            }
        }
    }


    public void updateStudent(Student student) {


        for (int i = 0; i < students.length; i++) {
            if (Objects.equals(students[i].getId(), student.getId())) {
                students[i] = student;
                System.out.println("Student has been updated");
                break;
            }
        }

    }


    public Student findOneStudent(String id) {

        Student student = new Student();

        for (Student value : students) {
            if (value != null && Objects.equals(value.getId(), id)) {

                student = value;
                return student;
            }
        }

        return student;
    }

    /////////group
    public void createGroup(Group group) {
        if (lastGroupIndex == groups.length - 1) {
            Group[] newGroups = new Group[groups.length * 2];
            System.arraycopy(groups, 0, newGroups, 0, groups.length);
            groups = newGroups;
            addGroup(group);
        } else {
            addGroup(group);
        }

        System.out.println("Group created");
    }

    public Group[] findAllGroups() {
        return groups;
    }


    private void addGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        groups[lastGroupIndex] = group;
        lastGroupIndex++;
    }

    public void deleteGroup(String id) {

        int index = 0;

        for (int i = 0; i < groups.length; i++) {
            if (Objects.equals(groups[i].getId(), id)) {
                index = i;
                Group[] newGroups = new Group[groups.length - 1];
                System.arraycopy(groups, 0, newGroups, 0, index);
                System.arraycopy(groups, index + 1, newGroups, index, groups.length - index - 1);
                groups = newGroups;
                lastGroupIndex--;
                System.out.println("Group with name " + id + " deleted");
                break;
            }
        }
    }


    public void updateGroup(Group group) {

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && Objects.equals(groups[i].getId(), group.getId())) {
                groups[i] = group;
                System.out.println("Group has been updated");
                break;
            }
        }

    }


    public Group findOneGroup(String id) {

        Group group = new Group();

        for (Group value : groups) {
            if (value != null && Objects.equals(value.getId(), id)) {
                group = value;
                return group;
            }
        }

        return group;
    }

    /////////group-student

    public void createGroupStudent(GroupStudent groupStudent) {
        if (lastGroupStudentIndex == groupStudents.length - 1) {
            GroupStudent[] newGroupStudents = new GroupStudent[groupStudents.length * 2];
            System.arraycopy(groupStudents, 0, newGroupStudents, 0, groupStudents.length);
            groupStudents = newGroupStudents;

        }
        groupStudents[lastGroupStudentIndex] = groupStudent;
        lastGroupStudentIndex++;

    }


}
