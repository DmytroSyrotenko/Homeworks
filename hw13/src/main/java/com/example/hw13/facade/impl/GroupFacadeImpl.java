package com.example.hw13.facade.impl;

import com.example.hw13.dto.request.GroupRequestDto;
import com.example.hw13.dto.response.GroupResponseDto;
import com.example.hw13.entity.Group;
import com.example.hw13.entity.Student;
import com.example.hw13.facade.GroupFacade;
import com.example.hw13.service.GroupCrudService;
import com.example.hw13.service.StudentCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class GroupFacadeImpl implements GroupFacade {
    private final GroupCrudService groupCrudService;
    private final StudentCrudService studentCrudService;

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setName(groupRequestDto.getName());
        group.setGeneralInfo(groupRequestDto.getGeneralInfo());
        groupCrudService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, Long id) {
        Group group = groupCrudService.findById(id);
        group.setName(groupRequestDto.getName());
        group.setGeneralInfo(groupRequestDto.getGeneralInfo());
        groupCrudService.update(group);
    }

    @Override
    public void delete(Long id) {
        groupCrudService.delete(id);
    }

    @Override
    public GroupResponseDto findById(Long id) {
        return new GroupResponseDto(groupCrudService.findById(id));
    }

    @Override
    public Collection<GroupResponseDto> findAll() {
        return groupCrudService.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .toList();
    }

    @Override
    public void attachStudentToGroup(GroupRequestDto groupRequestDto, Long groupId) {
        Group group = groupCrudService.findById(groupId);
        Student student = studentCrudService.findById(groupRequestDto.getStudentIdForDeleteOrAttach());
        group.getStudents().add(student);
        groupCrudService.update(group);
    }

    @Override
    public void removeStudentFromGroup(GroupRequestDto groupRequestDto, Long studentItToDelete) {

    }
}