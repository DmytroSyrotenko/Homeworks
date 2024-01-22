package com.example.api.dto.response.product;

import com.example.api.dto.response.ResponseDto;
import com.example.exceptions.NotValidFieldDataException;
import com.example.persistence.product.Product;
import com.example.persistence.product.ProductImage;
import com.example.persistence.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductPlpDto extends ResponseDto {

    private String name;
    private String image;
    private String description;
    private String minPrice;
    private String maxPrice;

    public ProductPlpDto(Product product, List<ProductVariant> variants) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        Set<ProductImage> images = product.getProductImages();
        if (CollectionUtils.isNotEmpty(images)) {
            ProductImage productImage = images
                    .stream()
                    .filter(ProductImage::getMainImage)
                    .findFirst()
                    .orElseThrow(() -> new NotValidFieldDataException("Main image not found"));
            this.image = productImage.getImageUrl();
        }
        if (CollectionUtils.isEmpty(variants)) {
            throw new NotValidFieldDataException("Product variants is not present");
        }
        ProductVariant max = variants.stream().max(ProductVariant::compareTo).get();
        ProductVariant min = variants.stream().min(ProductVariant::compareTo).get();
        this.minPrice = min.getPrice().toString();
        this.maxPrice = max.getPrice().toString();
    }
}
