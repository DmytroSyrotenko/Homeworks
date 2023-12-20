package org.example.controller.relations;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Group;
import org.example.entity.Student;
import org.example.service.GroupCrudService;
import org.example.service.impl.GroupCrudServiceImpl;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

public class FindAllStudentsByGroup extends HttpServlet {

    private final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] idArr = parameterMap.get("id");
        Long id = Long.parseLong(idArr[0]);
        Group group = groupCrudService.findOne(id);
        Collection<Student> students = group.getStudents();
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
            printWriter.write("Review students of group " + group.getGroupName());
            printWriter.write("</h1>");
            printWriter.write("<form action=\"/hw12/groups\">");
            printWriter.write("<button type=\"submit\">Back to groups</button>");
            printWriter.write("</form>");
            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Id</th>");
            printWriter.write("<th>Student First Name</th>");
            printWriter.write("<th>Student Last Name</th>");
            printWriter.write("<th>Student Age</th>");
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
                printWriter.write("</tr>");
            }
            printWriter.write("</table>");
            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (
                Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
