package com.example.api.dto.response.cart;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto extends ResponseDto {

    private Boolean active;
    private Integer totalPrice;
    private List<CartEntryDto> entries;

    public CartDto(Cart cart, List<CartEntryDto> cartEntryDto) {
        setId(cart.getId());
        this.active = cart.getActive();
        this.entries = cartEntryDto;
        this.totalPrice = cartEntryDto.stream().mapToInt(CartEntryDto::getSum).sum();
    }
}
