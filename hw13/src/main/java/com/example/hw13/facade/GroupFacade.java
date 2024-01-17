package com.example.hw13.facade;

import com.example.hw13.dto.request.GroupRequestDto;
import com.example.hw13.dto.response.GroupResponseDto;

public interface GroupFacade extends CrudFacade<GroupRequestDto, GroupResponseDto>{

     void attachStudentToGroup(GroupRequestDto groupRequestDto,Long studentItToDelete);
     void removeStudentFromGroup(GroupRequestDto groupRequestDto,Long studentItToDelete);
}
