package com.example.service.cabinet;

import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.persistence.entity.user.Personal;

public interface CabinetService {

    Personal getActiveUserInfo();
    void updateActiveUserInfo(UpdateUserFromFrontDto dto);
}
