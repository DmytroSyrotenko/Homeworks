package org.example.service;


import org.example.entity.Group;

public interface GroupCrudService extends CrudService<Group> {

    void addStudentToGroup(Long groupId, Long studentId);

    void deleteStudentFromGroup(Long groupId, Long studentId);

}