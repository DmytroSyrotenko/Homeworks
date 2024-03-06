package com.example.api.dto.response.cart;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.order.Order;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDto extends ResponseDto {

    private String date;
    private String totalOrderSum;


    public OrderDto(Order order) {
        setId(order.getId());
        this.date = String.valueOf(order.getDate()).substring(0,19);
        this.totalOrderSum = String.valueOf(order.getTotalPrice());
    }
}
