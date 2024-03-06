package com.example.service.cabinet.impl;

import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.repository.user.PersonalRepository;
import com.example.service.cabinet.CabinetService;
import com.example.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CabinetServiceImpl implements CabinetService {

    private final PersonalRepository personalRepository;

    @Override
    public Personal getActiveUserInfo() {
        String userName = SecurityUtil.getUsername();
        return personalRepository.findByLogin(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void updateActiveUserInfo(UpdateUserFromFrontDto dto) {
        Personal personal = getActiveUserInfo();
        personal.setFirstName(dto.getFirstName());
        personal.setLastName(dto.getLastName());
        personal.setLogin(dto.getEmail());
        personal.setBalance(dto.getBalance());
        personal.setDelivery_info(dto.getDeliveryInfo());
        personalRepository.save(personal);
    }
}
