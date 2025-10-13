package org.example;

import org.example.product.ProductService;
import org.example.product.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ProductsEndpointDebug {

    private final ProductService service;

    ProductsEndpointDebug(ProductService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/products")
    public List<ProductDto> getAll() {
        return service.findAll();
    }
}
