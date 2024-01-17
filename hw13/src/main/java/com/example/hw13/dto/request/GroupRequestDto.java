package com.example.hw13.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupRequestDto extends RequestDto {

    private String name;
    private String generalInfo;
    private Long studentIdForDeleteOrAttach;
}
