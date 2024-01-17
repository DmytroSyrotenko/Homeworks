package com.example.hw13.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class StudentRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private Integer age;

}
