package com.example.hw13.service.impl;

import com.example.hw13.entity.Group;
import com.example.hw13.repository.GroupRepository;
import com.example.hw13.service.GroupCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
@Transactional
@AllArgsConstructor
public class GroupCrudServiceImpl implements GroupCrudService {

    private final GroupRepository groupRepository;

    @Override
    public void create(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public void update(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collection<Group> findAll() {
        return groupRepository.findAll();
    }
}
