package syrotenko.ua.controller;


import syrotenko.ua.entity.Group;
import syrotenko.ua.entity.GroupStudent;
import syrotenko.ua.entity.Student;
import syrotenko.ua.service.GroupCrudService;
import syrotenko.ua.service.StudentCrudService;
import syrotenko.ua.service.impl.GroupCrudServiceImpl;
import syrotenko.ua.service.impl.GroupStudentCrudServiceImpl;
import syrotenko.ua.service.impl.StudentCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class MainController {
    StudentCrudService studentCS = new StudentCrudServiceImpl();
    GroupCrudService groupCS = new GroupCrudServiceImpl();
    GroupStudentCrudServiceImpl groupStudentCS = new GroupStudentCrudServiceImpl();


    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want CREATE STUDENT please enter 1");
        System.out.println("If you want UPDATE STUDENT please enter 2");
        System.out.println("If you want DELETE STUDENT please enter 3");
        System.out.println("If you want FIND ONE STUDENT please enter 4");
        System.out.println("If you want FIND ALL STUDENTS please enter 5");
        System.out.println();
        System.out.println("If you want CREATE GROUP please enter 6");
        System.out.println("If you want UPDATE GROUP please enter 7");
        System.out.println("If you want DELETE GROUP please enter 8");
        System.out.println("If you want FIND ONE GROUP please enter 9");
        System.out.println("If you want FIND ALL GROUPS please enter 10");
        System.out.println();
        System.out.println("If you want ADD STUDENT TO GROUP please enter 15");
        System.out.println("If you want find All Groups And Students please enter 16");
        System.out.println("If you want DELETE STUDENT FROM GROUP please enter 17");
        System.out.println();
        System.out.println("If you want CLOSE app please enter 0");
    }


    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> createStudent(bufferedReader);
            case "2" -> updateStudent(bufferedReader);
            case "3" -> deleteStudent(bufferedReader);
            case "4" -> findOneStudent(bufferedReader);
            case "5" -> findAllStudents();

            case "6" -> createGroup(bufferedReader);
            case "7" -> updateGroup(bufferedReader);
            case "8" -> deleteGroup(bufferedReader);
            case "9" -> findOneGroup(bufferedReader);
            case "10" -> findAllGroups();

            case "15" -> addStudentToGroup(bufferedReader);
            case "16" -> findAllGroupsAndStudents();
            case "17" -> deleteStudentFromGroup(bufferedReader);

            case "0" -> System.exit(0);
            default -> System.out.println("Incorrect data.Choose actual case");

        }
    }

    /*student*/
    private void createStudent(BufferedReader reader) throws IOException {
        System.out.println("Please enter firstName");
        String fn = reader.readLine();
        System.out.println("Please enter lastName");
        String ln = reader.readLine();
        int age = 0;
        System.out.println("Please enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data.Please enter a number");
            age = Integer.parseInt(reader.readLine());
        }
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        studentCS.create(student);
    }

    private void updateStudent(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to update a student");
        String id = reader.readLine();
        System.out.println("Please enter new firstName");
        String fn = reader.readLine();
        System.out.println("Please enter new lastName");
        String ln = reader.readLine();
        int age = 0;
        System.out.println("Please enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data.Please enter a number");
            age = Integer.parseInt(reader.readLine());
        }
        Student student = new Student();
        student.setId(id);
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        studentCS.update(student);
    }

    private void findAllStudents() {
        Student[] students = studentCS.findAll();
        for (Student student : students) {
            if (student != null) {
                System.out.println("--------Requested info below--------");
                printS(student);
            }
        }
    }

    private void findOneStudent(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to find one student");
        String id = reader.readLine();
        Student student = studentCS.findOne(id);
        if (student.getId() != null) {
            System.out.println("--------Requested info below--------");
            printS(student);
        }
    }

    private void deleteStudent(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to delete a student");
        String id = reader.readLine();
        studentCS.delete(id);

    }


    private void printS(Student student) {
        System.out.println("id = " + student.getId());
        System.out.println("firstName = " + student.getFirstName());
        System.out.println("lastName = " + student.getLastName());
        System.out.println("age = " + student.getAge());
    }

    /*group*/
    private void createGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter groupName");
        String name = reader.readLine();
        System.out.println("Please enter teachers name ");
        String teacher = reader.readLine();
        Group group = new Group();
        group.setName(name);
        group.setTeacher(teacher);
        groupCS.create(group);
    }


    private void updateGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter groupId to update a group");
        String id = reader.readLine();
        System.out.println("Please enter  new name to update a group");
        String newName = reader.readLine();
        System.out.println("Please enter new teachers name ");
        String newTeacher = reader.readLine();
        Group group = new Group();
        group.setId(id);
        group.setName(newName);
        group.setTeacher(newTeacher);
        groupCS.update(group);
    }


    private void deleteGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to delete a group");
        String id = reader.readLine();
        groupCS.delete(id);
    }


    private void findOneGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter groupId to find one group");
        String id = reader.readLine();
        Group group = groupCS.findOne(id);
        if (group.getId() != null) {
            printG(group);
        }
    }

    private void findAllGroups() {
        Group[] groups = groupCS.findAll();
        for (Group group : groups) {
            if (group != null) {
                System.out.println("----------------Requested info below---------------------------------------");
                printG(group);
            }
        }
    }

    private void printG(Group group) {
        System.out.println("Group id = " + group.getId());
        System.out.println("Group name = " + group.getName());
        System.out.println("Group teacher = " + group.getTeacher());
    }

    /*group-student*/
    private void addStudentToGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter groupId");
        String groupId = reader.readLine();
        System.out.println("Please enter studentId");
        String studentId = reader.readLine();
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setGroupId(groupId);
        groupStudent.setStudentId(studentId);
        groupStudentCS.createGroupStudent(groupStudent);
    }

    private void deleteStudentFromGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter studentId");
        String studentId = reader.readLine();
        System.out.println("Please enter groupId");
        String groupId = reader.readLine();
        groupStudentCS.deleteGroupStudent(studentId,groupId);
    }

    private void findAllGroupsAndStudents() {
        GroupStudent[] groupStudents = groupStudentCS.findAllGroupsAndStudents();
        Student[] students = studentCS.findAll();
        Group[] groups = groupCS.findAll();

        for (GroupStudent groupStudent : groupStudents) {

            if (groupStudent != null) {
                System.out.println("----------------Requested info below---------------------------------------");
                System.out.println("Group Id: " + groupStudent.getGroupId());

                for (int i = 0; i < groups.length - 1; i++) {
                    if (groups[i] != null && Objects.equals(groupStudent.getGroupId(), groups[i].getId())) {
                        System.out.println("Group name: " + groups[i].getName());
                        System.out.println("Group teacher: " + groups[i].getTeacher());
                        break;
                    }
                }
                System.out.println("Student Id: " + groupStudent.getStudentId());

                for (int i = 0; i < students.length - 1; i++) {
                    if (students[i] !=null &&Objects.equals(groupStudent.getStudentId(), students[i].getId())) {
                        System.out.println("Students first name: " + students[i].getFirstName());
                        System.out.println("Students last name: " + students[i].getLastName());
                        System.out.println("Students age: " + students[i].getAge());
                        break;
                    }
                }
            }
        }
    }
}
