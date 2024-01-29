package com.example.facade;

import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.api.dto.response.cabinet.UserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface CabinetFacade {

    UserInfoDto getUserInfo();

    void updateActiveUserInfo(UpdateUserFromFrontDto dto);
}
