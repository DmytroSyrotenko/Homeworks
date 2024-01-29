package com.example.api.controller.personal;


import com.example.api.dto.request.cabinet.UpdateUserFromFrontDto;
import com.example.api.dto.response.cabinet.UserInfoDto;
import com.example.facade.CabinetFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/personal/cabinet")
public class CabinetController {

    private final CabinetFacade cabinetFacade;


    @GetMapping
    public ResponseEntity<UserInfoDto> getActiveUserData() {
        return ResponseEntity.ok(cabinetFacade.getUserInfo());
    }
    @PostMapping
    public ResponseEntity<String> updateUserData(@RequestBody UpdateUserFromFrontDto dto) {
        cabinetFacade.updateActiveUserInfo(dto);
        return ResponseEntity.ok().build();
    }



}
