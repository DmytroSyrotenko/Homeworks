package org.example.controller.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.StudentCrudService;
import org.example.service.impl.StudentCrudServiceImpl;
import java.io.IOException;

public class StudentDeleteController extends HttpServlet {

    private final StudentCrudService studentCrudService = new StudentCrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        studentCrudService.delete(id);
        resp.sendRedirect("students");
    }
}
