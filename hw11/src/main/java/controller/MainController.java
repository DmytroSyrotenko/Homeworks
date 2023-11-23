package controller;

import entity.Group;
import entity.Student;
import controller.myReader.MyReader;
import service.GroupCrudService;
import service.StudentCrudService;
import service.impl.GroupCrudServiceImpl;
import service.impl.StudentCrudServiceImpl;
import java.util.Collection;
import java.util.Set;


public class MainController {

    final StudentCrudService studentCrudService = new StudentCrudServiceImpl();
    final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    public void start() {
        MyReader reader = new MyReader();
        menu();
        String position;
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    private void menu() {
        System.out.println("********************************************");
        System.out.println("Create student enter 1");
        System.out.println("Update student enter 2");
        System.out.println("Delete student enter 3");
        System.out.println("Find student enter 4");
        System.out.println("Find all students enter 5");
        System.out.println("--------");
        System.out.println("Create group enter 6");
        System.out.println("Update group enter 7");
        System.out.println("Delete group enter 8");
        System.out.println("Find group enter 9");
        System.out.println("Find all groups enter 10");
        System.out.println("--------");
        System.out.println("Add student to group enter 11");
        System.out.println("Delete student from group enter 12");
        System.out.println("Find all students in group enter 13");
        System.out.println("Find all groups for student enter 14");
        System.out.println("--------");
        System.out.println("Exit program enter 0");
        System.out.println("********************************************");
    }


    private void crud(String position, MyReader reader) {

        switch (position) {
            case "1" -> createStudent(reader);
            case "2" -> updateStudent(reader);
            case "3" -> deleteStudent(reader);
            case "4" -> findOneStudent(reader);
            case "5" -> findAllStudents();
            case "6" -> createGroup(reader);
            case "7" -> updateGroup(reader);
            case "8" -> deleteGroup(reader);
            case "9" -> findOneGroup(reader);
            case "10" -> findAllGroups();
            case "11" -> addStudentToGroup(reader);
            case "12" -> deleteStudentFromGroup(reader);
            case "13" -> findAllStudentsInGroup(reader);
            case "14" -> findAllGroupsForStudent(reader);
            case "0" -> System.exit(0);
        }
    }


    private void createStudent(MyReader reader) {
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        int age;
        System.out.println("Enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        studentCrudService.create(student);
    }

    private void updateStudent(MyReader reader) {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        int age;
        System.out.println("Enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        student.setId(id);
        studentCrudService.update(student);
    }

    private void deleteStudent(MyReader reader) {
        System.out.println("Enter student id");
        Long id = Long.valueOf(reader.readLine());
        studentCrudService.delete(id);
    }

    private void findOneStudent(MyReader reader) {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        Student student = studentCrudService.findOne(id);
        System.out.println("----");
        System.out.println("Id = " + student.getId());
        System.out.println("First name = " + student.getFirstName());
        System.out.println("Last name = " + student.getLastName());
        System.out.println("Age = " + student.getAge());
        System.out.println("----");
    }

    private void findAllStudents() {
        Collection<Student> collection = studentCrudService.findAll();
        for (Student student : collection) {
            System.out.println("student = " + student);
        }
    }

    private void createGroup(MyReader reader) {
        System.out.println("Enter group name");
        String gn = reader.readLine();
        System.out.println("Enter teachers name");
        String tn = reader.readLine();
        Group group = new Group();
        group.setGroupName(gn);
        group.setTeacher(tn);
        groupCrudService.create(group);
    }

    private void updateGroup(MyReader reader) {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Enter group name");
        String gn = reader.readLine();
        System.out.println("Enter teachers name");
        String tn = reader.readLine();
        Group group = new Group();
        group.setId(id);
        group.setGroupName(gn);
        group.setTeacher(tn);
        groupCrudService.update(group);
    }

    private void deleteGroup(MyReader reader) {
        System.out.println("Enter group id");
        Long id = Long.valueOf(reader.readLine());
        groupCrudService.delete(id);
    }

    private void findOneGroup(MyReader reader) {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        Group group = groupCrudService.findOne(id);
        System.out.println("----");
        System.out.println("Id = " + group.getId());
        System.out.println("Group name = " + group.getGroupName());
        System.out.println("Groups teacher = " + group.getTeacher());
        System.out.println("----");
    }

    private void findAllGroups() {
        Collection<Group> collection = groupCrudService.findAll();
        for (Group group : collection) {
            System.out.println("group = " + group);
        }
    }

    public void addStudentToGroup(MyReader reader) {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        groupCrudService.addStudentToGroup(grId, stId);
    }

    public void deleteStudentFromGroup(MyReader reader) {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        groupCrudService.deleteStudentFromGroup(grId, stId);
    }

    private void findAllStudentsInGroup(MyReader reader) {
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        Set<Student> students = groupCrudService.findOne(grId).getStudents();
        students.forEach(s -> System.out.println(s.toString()));
    }

    private void findAllGroupsForStudent(MyReader reader) {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        Set<Group> groups = studentCrudService.findOne(stId).getGroups();
        groups.forEach(g -> System.out.println(g.toString()));
    }
}
