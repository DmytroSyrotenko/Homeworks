package com.example.api.dto.response.product;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.product.ProductColor;
import com.example.persistence.entity.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantDto extends ResponseDto {

    private Integer ram;
    private Integer ssd;
    private String price;
    private String color;

    public ProductVariantDto(ProductVariant productVariant) {
        setId(productVariant.getId());
        this.ram = productVariant.getRam();
        this.ssd = productVariant.getSsd();
        this.price = productVariant.getPrice().toString();
        ProductColor productColor = productVariant.getProductColor();
        if (productColor != null) {
            this.color = productColor.getColor();
        }

    }

}
