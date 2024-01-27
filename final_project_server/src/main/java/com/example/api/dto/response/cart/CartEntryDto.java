package com.example.api.dto.response.cart;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.cart.CartEntry;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartEntryDto extends ResponseDto {

    private String name;
    private String image;
    private Integer quantity;
    private String price;

    public CartEntryDto(CartEntry cartEntry){
        setId(cartEntry.getId());
        setPrice(String.valueOf(cartEntry.getProductVariant().getPrice()));

    }
}
