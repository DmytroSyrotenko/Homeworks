package com.example.api.controller.product;

import com.example.api.dto.response.product.ProductPdpDto;
import com.example.api.dto.response.product.ProductPlpDto;
import com.example.facade.ProductPdpFacade;
import com.example.facade.ProductPlpFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductPlpFacade productPlpFacade;
    private final ProductPdpFacade productPdpFacade;


    @GetMapping("/plp")
    public ResponseEntity<List<ProductPlpDto>> findAll() {
        return ResponseEntity.ok(productPlpFacade.findAll());
    }

    @GetMapping("/pdp/{productId}")
    public ResponseEntity<ProductPdpDto> findAllVariantsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productPdpFacade.findAllByProductId(productId));
    }
}
