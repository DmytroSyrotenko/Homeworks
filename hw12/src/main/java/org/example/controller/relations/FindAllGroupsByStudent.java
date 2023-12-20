package org.example.controller.relations;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Group;
import org.example.entity.Student;
import org.example.service.StudentCrudService;
import org.example.service.impl.StudentCrudServiceImpl;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

public class FindAllGroupsByStudent extends HttpServlet {

    private final StudentCrudService studentCrudService = new StudentCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] idArr = parameterMap.get("id");
        Long id = Long.parseLong(idArr[0]);
        Student student = studentCrudService.findOne(id);
        Collection<Group> groups = student.getGroups();
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
            printWriter.write("Review groups of student " + student.getFirstName() + " " + student.getLastName());
            printWriter.write("</h1>");
            printWriter.write("<form action=\"/hw12/students\">");
            printWriter.write("<button type=\"submit\">Back to students</button>");
            printWriter.write("</form>");
            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Id</th>");
            printWriter.write("<th>Group Name</th>");
            printWriter.write("<th>Group Teacher</th>");
            printWriter.write("</tr>");
            for (Group group : groups) {
                printWriter.write("<tr>");
                printWriter.write("<td>");
                printWriter.write(group.getId().toString());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(group.getGroupName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(group.getTeacher());
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
