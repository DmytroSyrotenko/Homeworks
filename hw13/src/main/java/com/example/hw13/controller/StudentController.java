package com.example.hw13.controller;

import com.example.hw13.dto.request.StudentRequestDto;
import com.example.hw13.dto.response.StudentResponseDto;
import com.example.hw13.facade.StudentFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {
    private final StudentFacade studentFacade;

    @GetMapping
    public String allStudents(Model model) {
        model.addAttribute("students", studentFacade.findAll());
        return "/pages/students/student_all";
    }


    @GetMapping("/update/{id}")
    public String redirectToUpdateStudent(@PathVariable Long id, Model model) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        model.addAttribute("student", studentResponseDto);
        return "pages/students/student_update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute StudentRequestDto studentRequestDto) {
        studentFacade.update(studentRequestDto, id);
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String redirectToCreateStudent(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        return "pages/students/student_new";
    }

    @PostMapping("/new")
    public String createStudent(@ModelAttribute StudentRequestDto studentRequestDto) {
        studentFacade.create(studentRequestDto);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";

    }
}
