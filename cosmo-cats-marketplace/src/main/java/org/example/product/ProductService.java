package org.example.product;

import org.example.product.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
  private final Map<java.util.UUID, ProductDto> storage = new ConcurrentHashMap<>();

  public ProductService() {
    create(new ProductDto(null, "Galaxy Yarn", "Anti-gravity yarn ball", new BigDecimal("19.99"), "toys"));
    create(new ProductDto(null, "Comet Milk", "Fresh from the Oort cloud", new BigDecimal("7.49"), "food"));
  }

  public ProductDto create(ProductDto dto) {
    java.util.UUID id = Optional.ofNullable(dto.id()).orElse(java.util.UUID.randomUUID());
    ProductDto saved = new ProductDto(id, dto.name(), dto.description(), dto.price(), dto.category());
    storage.put(id, saved);
    return saved;
  }

  public java.util.List<ProductDto> findAll() { return new java.util.ArrayList<>(storage.values()); }

  public Optional<ProductDto> findById(java.util.UUID id) { return Optional.ofNullable(storage.get(id)); }

  public Optional<ProductDto> update(java.util.UUID id, ProductDto dto) {
    if (!storage.containsKey(id)) return Optional.empty();
    ProductDto updated = new ProductDto(id, dto.name(), dto.description(), dto.price(), dto.category());
    storage.put(id, updated);
    return Optional.of(updated);
  }

  public boolean delete(java.util.UUID id) { return storage.remove(id) != null; }
}
