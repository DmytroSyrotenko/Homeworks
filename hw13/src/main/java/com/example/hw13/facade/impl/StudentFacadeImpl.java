package com.example.hw13.facade.impl;

import com.example.hw13.dto.request.StudentRequestDto;
import com.example.hw13.dto.response.StudentResponseDto;
import com.example.hw13.entity.Student;
import com.example.hw13.facade.StudentFacade;
import com.example.hw13.service.StudentCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentFacadeImpl implements StudentFacade {

    private final StudentCrudService studentCrudService;

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setAge(studentRequestDto.getAge());
        studentCrudService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {
        Student student = studentCrudService.findById(id);
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setAge(studentRequestDto.getAge());
        studentCrudService.update(student);
    }

    @Override
    public void delete(Long id) {
        studentCrudService.delete(id);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentCrudService.findById(id));
    }

    @Override
    public Collection<StudentResponseDto> findAll() {
        return studentCrudService.findAll()
                .stream()
                .map(StudentResponseDto::new)
                .toList();
    }
}
