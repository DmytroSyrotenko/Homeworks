package org.example.controller.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.MapUtils;
import org.example.entity.Student;
import org.example.service.StudentCrudService;
import org.example.service.impl.StudentCrudServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StudentUpdateController extends HttpServlet {

    private final StudentCrudService studentCrudService = new StudentCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        Student student = studentCrudService.findOne(id);
        resp.setStatus(200);
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang='en'>");
            printWriter.write("<body>");
            printWriter.write("<h1>");
            printWriter.write("Update student");
            printWriter.write("</h1>");
            printWriter.write("<form method='post' action='/hw12/students-update'>");
            printWriter.write("<label for='firstName'>First name:</label><br>");
            printWriter.write("<input type='text' id='firstName' name='studentFirstName' value='" + student.getFirstName() + "'/><br><br>");
            printWriter.write("<label for='lastName'>Last name:</label><br>");
            printWriter.write("<input type='text' id='lastName' name='studentLastName' value='" + student.getLastName() + "'/><br><br>");
            printWriter.write("<label for='age'>Age:</label><br>");
            printWriter.write("<input type='number' id='age' name='studentAge' value='" + student.getAge() + "'/><br><br>");
            printWriter.write("<input type='hidden' id='id' name='id' value='" + student.getId() + "'/>");
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
            Student student = studentCrudService.findOne(id);
            parameterMap.forEach((k, v) -> {
                if (k.equals("studentFirstName")) {
                    String firstName = v[0];
                    student.setFirstName(firstName);
                }
                if (k.equals("studentLastName")) {
                    String lastName = v[0];
                    student.setLastName(lastName);
                }
                if (k.equals("studentAge")) {
                    Integer age = Integer.valueOf(v[0]);
                    student.setAge(age);
                }
            });
            studentCrudService.update(student);
        }
        resp.sendRedirect("students");
    }
}

