package org.example.controller.group;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.MapUtils;
import org.example.entity.Group;
import org.example.service.GroupCrudService;
import org.example.service.impl.GroupCrudServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GroupCreateController extends HttpServlet {

    private final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang='en'>");
            printWriter.write("<body>");
            printWriter.write("<h1>");
            printWriter.write("Create new group");
            printWriter.write("</h1>");
            printWriter.write("<form method='post' action='/hw12/groups-new'>");
            printWriter.write("<label for='groupName'>Group name:</label><br>");
            printWriter.write("<input type='text' id='groupName' name='groupName'/><br><br>");
            printWriter.write("<label for='groupTeacher'>Group teacher:</label><br>");
            printWriter.write("<input type='text' id='groupTeacher' name='groupTeacher'/><br><br>");
            printWriter.write("<input type='submit' value='Create'/>");
            printWriter.write("</form>");
            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(201);
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            Group group = new Group();
            parameterMap.forEach((k, v) -> {
                if (k.equals("groupName")) {
                    String groupName = v[0];
                    group.setGroupName(groupName);
                }
                if (k.equals("groupTeacher")) {
                    String groupTeacher = v[0];
                    group.setTeacher(groupTeacher);
                }
            });
            groupCrudService.create(group);
        }
        resp.sendRedirect("groups");
    }
}
