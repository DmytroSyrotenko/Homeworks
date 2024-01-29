package com.example.api.dto.response.cabinet;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.user.Personal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private String balance;
    private String deliveryInfo;
    private String email;

    public UserInfoDto(Personal personal) {
        setId(personal.getId());
        this.email = personal.getLogin();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
        this.deliveryInfo = personal.getDelivery_info();
        this.balance = personal.getBalance();
    }
}
