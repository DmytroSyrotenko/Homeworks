package com.example.api.dto.response.product;

import com.example.api.dto.response.ResponseDto;
import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductImage;
import com.example.persistence.entity.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductPdpDto extends ResponseDto {

    private String name;
    private String description;
    private String cpu;
    private String battery;
    private String camera;
    private String displayResolution;

    private List<String> images;
    private List<ProductVariantDto> variants;

    public ProductPdpDto(Product product, List<ProductVariant> productVariants) {
        setId(product.getId());
        this.name= product.getName();
        this.description= product.getDescription();
        Set<ProductImage> imageSet = product.getProductImages();
        if(CollectionUtils.isNotEmpty(imageSet)){
            this.images=imageSet
                    .stream()
                    .map(ProductImage::getImageUrl)
                    .toList();
        }
        if(CollectionUtils.isNotEmpty(productVariants)){
            setCpu(productVariants.stream().findFirst().get().getCpu());
            setBattery(productVariants.stream().findFirst().get().getBattery());
            setCamera(productVariants.stream().findFirst().get().getCamera());
            setDisplayResolution(productVariants.stream().findFirst().get().getDisplayResolution());
            this.variants= productVariants.stream().map(ProductVariantDto::new).toList();
        }
    }

}