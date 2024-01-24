package com.example.api.dto.response.product;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.product.ProductColor;
import com.example.persistence.product.ProductVariant;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantDto extends ResponseDto {

    private String cpu;

    private Integer ram;

    private Integer ssd;

    private String camera;

    private String displayResolution;

    private String price;

    private String color;

    public ProductVariantDto(ProductVariant productVariant) {
        setId(productVariant.getId());
        this.cpu = productVariant.getCpu();
        this.ram = productVariant.getRam();
        this.ssd = productVariant.getSsd();
        this.camera = productVariant.getCamera();
        this.displayResolution = productVariant.getDisplayResolution();
        this.price = productVariant.getPrice().toString();
        ProductColor productColor = productVariant.getProductColor();
        if (productColor != null) {
            this.color = productColor.getColor();
        }

    }

}
