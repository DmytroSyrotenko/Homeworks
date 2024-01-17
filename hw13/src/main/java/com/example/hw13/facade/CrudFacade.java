package com.example.hw13.facade;

import com.example.hw13.dto.request.RequestDto;
import com.example.hw13.dto.response.ResponseDto;

import java.util.Collection;

public interface CrudFacade<REQUEST extends RequestDto, RESPONSE extends ResponseDto> {

    void create(REQUEST request);

    void update(REQUEST request, Long id);

    void delete(Long id);

    RESPONSE findById(Long id);

    Collection<RESPONSE> findAll();

}
