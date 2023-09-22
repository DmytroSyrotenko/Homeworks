package qq.module1.controller;

import qq.module1.Impl.GroupStudentCrudServiceImpl;
import qq.module1.Impl.interfaces.StudentCrudService;
import qq.module1.entity.Group;
import qq.module1.entity.GroupStudent;
import qq.module1.entity.Student;
import qq.module1.Impl.interfaces.GroupCrudService;
import qq.module1.Impl.GroupCrudServiceImpl;
import qq.module1.Impl.StudentCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    StudentCrudService studentCrudServiceMPO = new StudentCrudServiceImpl();
    GroupCrudService groupCrudServiceMPO = new GroupCrudServiceImpl();
    GroupStudentCrudServiceImpl groupStudentCrudServiceMPO = new GroupStudentCrudServiceImpl();


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
        System.out.println("If you want DELETE STUDENT FROM GROUP please enter 16");
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
//            case "16" -> deleteStudentFromGroup();


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
        System.out.println("Please enter new age");
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
        studentCrudServiceMPO.create(student);
    }


    private void findAllStudents() {
        Student[] students = studentCrudServiceMPO.findAll();

        for (Student student : students) {
            if (student != null) {
                printStudent(student);
            }
        }
    }

    private void deleteStudent(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to delete a student");
        String id = reader.readLine();

        if(isPresentStudent(id)){
            studentCrudServiceMPO.delete(id);
        }


    }

    private boolean isPresentStudent(String id) {
        boolean b = false;
        Student[] students = studentCrudServiceMPO.findAll();

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                System.out.println("Student found");
                b = true;
                break;
            } else if (i == students.length - 1) {
                System.out.println("Id not found,please try again");
                return b;
            }

        }
        return b;
    }


    private void updateStudent(BufferedReader reader) throws IOException {

        System.out.println("Please enter id to update a student");
        String id = reader.readLine();


        if (isPresentStudent(id)) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter new firstName");
            String fn = bufferedReader.readLine();
            System.out.println("Please enter new lastName");
            String ln = bufferedReader.readLine();

            int age = 0;
            System.out.println("Please enter new age");
            try {
                age = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect data.Please enter a number");
                age = Integer.parseInt(bufferedReader.readLine());
            }

            Student student = new Student();
            student.setId(id);
            student.setFirstName(fn);
            student.setLastName(ln);
            student.setAge(age);

            studentCrudServiceMPO.update(student);
        }
    }

    private void printStudent(Student student) {
        System.out.println("----------------Requested info below---------------------------------------");
        System.out.println("id = " + student.getId());
        System.out.println("firstName = " + student.getFirstName());
        System.out.println("lastName = " + student.getLastName());
        System.out.println("age = " + student.getAge());

//                System.out.println("group = " + student.get group); обязательно внести!!!!
    }

    private void findOneStudent(BufferedReader reader) throws IOException {

        System.out.println("Please enter id to find one student");
        String id = reader.readLine();

        Student student = studentCrudServiceMPO.findOne(id);

        if (student.getId() != null) {
            printStudent(student);
        } else {
            System.out.println("Id not found,please try again");
        }
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
        groupCrudServiceMPO.create(group);

    }

    private boolean isPresentGroup(String id) {
        boolean b = false;
        Group[] groups = groupCrudServiceMPO.findAll();

        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && groups[i].getId().equals(id)) {
                System.out.println("Group found");
                b = true;
                break;
            } else if (i == groups.length - 1) {
                System.out.println("Group not found,please try again");
                return b;
            }

        }
        return b;
    }

    private void updateGroup(BufferedReader reader) throws IOException {

        System.out.println("Please enter groupId to update a group");
        String id = reader.readLine();


        if (isPresentGroup(id)) {
            System.out.println("Please enter  new name to update a group");
            String newName = reader.readLine();
            System.out.println("Please enter new teachers name ");
            String newTeacher = reader.readLine();

            Group group = new Group();
            group.setId(id);
            group.setName(newName);
            group.setTeacher(newTeacher);

            groupCrudServiceMPO.update(group);
        }
    }

    private void deleteGroup(BufferedReader reader) throws IOException {
        System.out.println("Please enter id to delete a group");
        String id = reader.readLine();

        if (isPresentGroup(id)) {
            groupCrudServiceMPO.delete(id);
        }

    }

    private void printGroup(Group group) {
        System.out.println("----------------Requested info below---------------------------------------");
        System.out.println("Group id = " + group.getId());
        System.out.println("Group name = " + group.getName());
        System.out.println("Group teacher = " + group.getTeacher());
    }

    private void findOneGroup(BufferedReader reader) throws IOException {

        System.out.println("Please enter groupId to find one group");
        String id = reader.readLine();

        Group group = groupCrudServiceMPO.findOne(id);

        if (group.getId() != null) {
            printGroup(group);
        } else {
            System.out.println("Group not found,please try again");
        }
    }


    private void findAllGroups() {
        Group[] groups = groupCrudServiceMPO.findAll();

        for (Group group : groups) {
            if (group != null) {
                printGroup(group);
            }
        }
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
        groupStudentCrudServiceMPO.createGroupStudent(groupStudent);
    }

//    private void deleteStudentFromGroup() {
//      //  GroupStudent[] groupStudents = groupStudentCrudServiceMPO.findAllGroupsAndStudents();
//
//        for (GroupStudent groupStudent : groupStudents) {
//            if (groupStudent != null) {
//
//                System.out.println("Group Id"+groupStudent.getGroupId());
//                System.out.println("Student Id"+groupStudent.getStudentId());
//            }
//        }
//    }

}
