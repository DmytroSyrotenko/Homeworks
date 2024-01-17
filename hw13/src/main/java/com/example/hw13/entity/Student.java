package com.example.hw13.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private Set<Group> groups;

    @PreRemove
    private void deleteGroupRelationsBeforeKillStudent() {
        groups.forEach(group -> group.getStudents().remove(this));
    }


    public Student() {
        this.groups = new HashSet<>();
    }

}
