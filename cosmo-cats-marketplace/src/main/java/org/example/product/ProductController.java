package org.example.product;

import org.example.product.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) { this.service = service; }

  @GetMapping
  public List<ProductDto> getAll() { return service.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getOne(@PathVariable UUID id) {
    return service.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
    ProductDto saved = service.create(dto);
    return ResponseEntity.created(URI.create("/api/v1/products/" + saved.id())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> update(@PathVariable UUID id, @RequestBody ProductDto dto) {
    return service.update(id, dto).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.deleteIfExists(id);
    return ResponseEntity.noContent().build(); // 204
  }
}
