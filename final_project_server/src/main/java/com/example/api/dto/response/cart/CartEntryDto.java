package com.example.api.dto.response.cart;

import com.example.api.dto.response.ResponseDto;
import com.example.exceptions.NotValidFieldDataException;
import com.example.persistence.entity.cart.CartEntry;
import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductImage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CartEntryDto extends ResponseDto {

    private String name;
    private String image;
    private String color;
    private String ssd;
    private String ram;
    private Integer quantity;
    private Integer price;
    private Integer sum;
    private Integer productId;

    public CartEntryDto(CartEntry cartEntry, Product product) {
        setId(cartEntry.getId());
        this.productId = Math.toIntExact(product.getId());
        this.quantity = cartEntry.getQuantity();
        this.color=cartEntry.getProductVariant().getProductColor().getColor();
        this.ssd= String.valueOf(cartEntry.getProductVariant().getSsd());
        this.ram= String.valueOf(cartEntry.getProductVariant().getRam());
        String productImage =
                product
                        .getProductImages()
                        .stream()
                        .filter(ProductImage::getMainImage)
                        .findFirst()
                        .get().getImageUrl();

        this.image = productImage;
        this.price = cartEntry.getProductVariant().getPrice().intValueExact();
        this.name = product.getName();
        this.sum = price*quantity;

    }
}
