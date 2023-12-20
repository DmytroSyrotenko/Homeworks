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

public class GroupUpdateController extends HttpServlet {

    private final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        Group group = groupCrudService.findOne(id);
        resp.setStatus(200);
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang='en'>");
            printWriter.write("<body>");
            printWriter.write("<h1>");
            printWriter.write("Update group");
            printWriter.write("</h1>");
            printWriter.write("<form method='post' action='/hw12/groups-update'>");
            printWriter.write("<label for='groupName'>Group name:</label><br>");
            printWriter.write("<input type='text' id='groupName' name='groupName' value='" + group.getGroupName() + "'/><br><br>");
            printWriter.write("<label for='groupTeacher'>Group teacher:</label><br>");
            printWriter.write("<input type='text' id='groupTeacher' name='groupTeacher' value='" + group.getTeacher() + "'/><br><br>");
            printWriter.write("<input type='hidden' id='id' name='id' value='" + group.getId() + "'/>");
            printWriter.write("<input type='submit' value='Update'/>");
            printWriter.write("</form>");
            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            String[] idArr = parameterMap.get("id");
            Long id = Long.parseLong(idArr[0]);
            Group group = groupCrudService.findOne(id);
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
            groupCrudService.update(group);
        }
        resp.sendRedirect("groups");
    }
}
