package com.example.facade.impl;


import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.api.dto.response.cabinet.UserInfoDto;
import com.example.service.cabinet.CabinetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CabinetFacade implements com.example.facade.CabinetFacade {

    private final CabinetService cabinetService;

    @Override
    public UserInfoDto getUserInfo() {
        return new UserInfoDto(cabinetService.getActiveUserInfo());
    }

    @Override
    public void updateActiveUserInfo(UpdateUserFromFrontDto dto) {
        cabinetService.updateActiveUserInfo(dto);
    }
}
