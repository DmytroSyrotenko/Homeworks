package org.example.controller.group;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Group;
import org.example.service.GroupCrudService;
import org.example.service.impl.GroupCrudServiceImpl;
import java.io.PrintWriter;
import java.util.Collection;

public class GroupReviewController extends HttpServlet {

    private final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        Collection<Group> groups = groupCrudService.findAll();
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
            printWriter.write("Review all groups");
            printWriter.write("</h1>");
            printWriter.write("<form action=\"/hw12/groups-new\">");
            printWriter.write("<button type=\"submit\">Create new group</button>");
            printWriter.write("</form>");
            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Id</th>");
            printWriter.write("<th>Group name</th>");
            printWriter.write("<th>Teacher</th>");
            printWriter.write("<th>Delete</th>");
            printWriter.write("<th>Update</th>");
            printWriter.write("<th>Attach student to group</th>");
            printWriter.write("<th>Remove student from group</th>");
            printWriter.write("<th>Watch all group students</th>");
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
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/groups-delete?id=" + group.getId() + "\">delete</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/groups-update?id=" + group.getId() + "\">update</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/groups-attachStudent?id=" + group.getId() + "\">attach</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/groups-removeStudent?id=" + group.getId() + "\">remove</a>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<a href=\"/hw12/groups-allStudents?id=" + group.getId() + "\">watch</a>");
                printWriter.write("</td>");
                printWriter.write("</tr>");
            }
            printWriter.write("</table>");
            printWriter.write("<form action=\"/hw12/students\">");
            printWriter.write("<button type=\"submit\">Go to students</button>");
            printWriter.write("</form>");
            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (
                Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
