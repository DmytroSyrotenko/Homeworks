package com.example.hw13.service.impl;

import com.example.hw13.entity.Student;
import com.example.hw13.repository.StudentRepository;
import com.example.hw13.service.StudentCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class StudentCrudServiceImpl implements StudentCrudService {

    private final StudentRepository studentRepository;


    @Override
    public void create(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void update(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }
}
