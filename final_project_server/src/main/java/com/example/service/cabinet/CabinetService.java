package com.example.service.cabinet;

import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.entity.user.User;

public interface CabinetService {

    Personal getActiveUserInfo();
    void updateActiveUserInfo(UpdateUserFromFrontDto dto);
}
