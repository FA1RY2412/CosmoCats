package org.example.product;

import org.example.product.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;

  public ProductController(ProductService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
    ProductDto saved = service.create(dto);
    return ResponseEntity.created(URI.create("/api/products/" + saved.id())).body(saved);
  }

  @GetMapping
  public List<ProductDto> getAll() { return service.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getOne(@PathVariable UUID id) {
    return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> update(@PathVariable UUID id, @Valid @RequestBody ProductDto dto) {
    return service.update(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
