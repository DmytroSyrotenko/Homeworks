package syrotenko.ua.db;

import syrotenko.ua.entity.Group;
import syrotenko.ua.entity.GroupStudent;
import syrotenko.ua.entity.Student;

import java.util.Objects;
import java.util.UUID;

public class Db {

    private Student[] students = new Student[2];
    private Group[] groups = new Group[2];
    private GroupStudent[] groupsStudents = new GroupStudent[2];
    private int lastStudentIndex = 0;
    private int lastGroupIndex = 0;
    private int lastGroupStudentIndex = 0;

    private static Db instance;

    private Db() {
    }

    ;

    public static Db getInstance() {
        if (instance != null) {
            return instance;
        } else {
            var db = new Db();
            instance = db;
            return db;
        }
    }

    /*student*/
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
                break;
            }
        }
    }

    public void updateStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (Objects.equals(students[i].getId(), student.getId())) {
                students[i] = student;
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

    /*group*/
    public void createGroup(Group group) {
        if (lastGroupIndex == groups.length - 1) {
            Group[] newGroups = new Group[groups.length * 2];
            System.arraycopy(groups, 0, newGroups, 0, groups.length);
            groups = newGroups;
            addGroup(group);
        } else {
            addGroup(group);
        }
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
                break;
            }
        }
    }

    public void updateGroup(Group group) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && Objects.equals(groups[i].getId(), group.getId())) {
                groups[i] = group;
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

    /*group-student*/

    public void createGroupStudent(GroupStudent groupStudent) {
        if (lastGroupStudentIndex == groupsStudents.length - 1) {
            GroupStudent[] newGroupStudents = new GroupStudent[groupsStudents.length * 2];
            System.arraycopy(groupsStudents, 0, newGroupStudents, 0, groupsStudents.length);
            groupsStudents = newGroupStudents;
        }
        groupsStudents[lastGroupStudentIndex] = groupStudent;
        lastGroupStudentIndex++;

    }

    public GroupStudent[] findAllGroupsAndStudents() {
        return groupsStudents;
    }

    public void deleteStudentFromGroup(String studentId, String groupId) {
        int index = 0;
        for (int i = 0; i < groupsStudents.length; i++) {
            if (Objects.equals(groupsStudents[i].getGroupId(), groupId) && Objects.equals(groupsStudents[i].getStudentId(), studentId)) {
                index = i;
                GroupStudent[] newGroupsStudents = new GroupStudent[groupsStudents.length - 1];
                System.arraycopy(groupsStudents, 0, newGroupsStudents, 0, index);
                System.arraycopy(groupsStudents, index + 1, newGroupsStudents, index, groupsStudents.length - index - 1);
                groupsStudents = newGroupsStudents;
                lastGroupStudentIndex--;
                break;
            }
        }
    }

    public void deleteStudentFromAllGroups(String studentId) {
        int index = 0;
        for (int i = 0; i < groupsStudents.length; i++) {
            if (groupsStudents[i]!=null && Objects.equals(groupsStudents[i].getStudentId(), studentId)) {
                groupsStudents[i]=null;
            }
        }
    }
    public void deleteGroupFromAllStudents(String groupId) {
        int index = 0;
        for (int i = 0; i < groupsStudents.length; i++) {
            if (groupsStudents[i]!=null && Objects.equals(groupsStudents[i].getGroupId(), groupId)) {
                groupsStudents[i]=null;
            }
        }
    }

    /*support methods*/
    public boolean isPresentStudent(String id) {
        boolean b = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                b = true;
                break;
            } else if (i == students.length - 1) {
                System.out.println("Id not found,please try again");
                return b;
            }
        }
        return b;
    }

    public boolean isPresentGroup(String id) {
        boolean b = false;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && groups[i].getId().equals(id)) {
                b = true;
                break;
            } else if (i == groups.length - 1) {
                System.out.println("Id not found,please try again");
                return b;
            }
        }
        return b;
    }

    public boolean isPresentGroupStudent(String groupId, String studentId) {
        boolean b = false;
        for (int i = 0; i < groupsStudents.length; i++) {
            if (groupsStudents[i] != null && groupsStudents[i].getGroupId().equals(groupId) && groupsStudents[i].getStudentId().equals(studentId)) {
                b = true;
                break;
            } else if (i == groups.length - 1) {
                System.out.println("Id not found,please try again");
                return b;
            }
        }
        return b;
    }
}
