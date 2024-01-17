package com.example.hw13.dto.response;

import com.example.hw13.entity.Group;
import com.example.hw13.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

@Getter
@Setter

public class StudentResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private List<GroupInfo> groupInfoList;

    public StudentResponseDto(Student student) {
        setId(student.getId());
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
        setAge(student.getAge());
        Set<Group> groups = student.getGroups();
        if (CollectionUtils.isNotEmpty(groups)) {
            List<GroupInfo> groupInfos = groups
                    .stream()
                    .map(GroupInfo::new)
                    .toList();
            setGroupInfoList(groupInfos);
        }
    }

    @Getter
    @Setter
    private static class GroupInfo {
        private Long groupId;
        private String groupName;

        GroupInfo(Group group) {
            setGroupId(group.getId());
            setGroupName(group.getName());
        }
    }
}
