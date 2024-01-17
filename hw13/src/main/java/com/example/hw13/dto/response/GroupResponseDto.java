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
public class GroupResponseDto extends ResponseDto {

    private String name;
    private int countOfStudents;
    private String generalInfo;
    List<StudentInfo> studentInfoList;
    private Long studentIdForDeleteOrAttach;

    public GroupResponseDto(Group group) {
        setId(group.getId());
        setName(group.getName());
        setGeneralInfo(group.getGeneralInfo());
        Set<Student> students = group.getStudents();
        if (CollectionUtils.isNotEmpty(students)) {
            countOfStudents = students.size();
            List<StudentInfo> studentInfos = students.stream()
                    .map(StudentInfo::new)
                    .toList();
            setStudentInfoList(studentInfos);
        } else {
            countOfStudents = 0;
        }
    }

    @Getter
    @Setter
    private static class StudentInfo {
        private Long studentId;
        private String firstName;
        private String lastName;
        private Integer age;

        StudentInfo(Student student) {
            setStudentId(student.getId());
            setFirstName(student.getFirstName());
            setLastName(student.getLastName());
            setAge(student.getAge());
        }
    }
}
