package com.example.api.dto.request.cabinet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //TODO kill
public class UpdateUserFromFrontDto {

    private String firstName;
    private String lastName;
    private String balance;
    private String deliveryInfo;
    private String email;

}
