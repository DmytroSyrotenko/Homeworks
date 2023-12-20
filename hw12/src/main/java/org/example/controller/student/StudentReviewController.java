package org.example.controller.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Student;
import org.example.service.StudentCrudService;
import org.example.service.impl.StudentCrudServiceImpl;
import java.io.PrintWriter;
import java.util.Collection;

public class StudentReviewController extends HttpServlet {

    private final StudentCrudService studentCrudService = new StudentCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        Collection<Student> students = studentCrudService.findAll();
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang=\"en\">");
            printWriter.write("<head>");
            printWriter.write("<style>");
            printWriter.write("table {");
            printWriter.write("font-family: arial, sans-serif;");
            printWriter.write("border-collapse: collapse;");
            printWriter.write("width: 100%;");
            printWriter.write("}");
            printWriter.write("td, th {");
            printWriter.write("border: 1px solid #dddddd;");
            printWriter.write("text-align: left;");
            printWriter.write("padding: 8px;");
            printWriter.write("}");
            printWriter.write("tr:nth-child(even) {");
            printWriter.write("background-color: #dddddd;");
            printWriter.write("}");
            printWriter.write("</style>");
            printWriter.write("</head>");
            printWriter.write("<body>");
            printWriter.write("<h1>");
            printWriter.write("Review all students");
            printWriter.write("</h1>");
            printWriter.write("<form action=\"/hw12/students-new\">");
            printWriter.write("<button type=\"submit\">Create new student</button>");
            printWriter.write("</form>");
            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Id</th>");
            printWriter.write("<th>First name</th>");
            printWriter.write("<th>Last name</th>");
            printWriter.write("<th>Age</th>");
            printWriter.write("<th>Delete</th>");
            printWriter.write("<th>Update</th>");
            printWriter.write("<th>Watch all student groups</th>");
            printWriter.write("</tr>");
            for (Student student : students) {
                printWriter.write("<tr>");
                printWriter.write("<td>");
                printWriter.write(student.getId().toString());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(student.getFirstName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(student.getLastName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(student.getAge().toString());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/students-delete?id=" + student.getId() + "\">delete</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/students-update?id=" + student.getId() + "\">update</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/students-allGroups?id=" + student.getId() + "\">watch</a>");
                printWriter.write("</td>");
                printWriter.write("</tr>");
            }
            printWriter.write("</table>");
            printWriter.write("<form action=\"/hw12/groups\">");
            printWriter.write("<button type=\"submit\">Go to groups</button>");
            printWriter.write("</form>");
            printWriter.write("</body>");
            printWriter.write("</html>");

        } catch (
                Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}