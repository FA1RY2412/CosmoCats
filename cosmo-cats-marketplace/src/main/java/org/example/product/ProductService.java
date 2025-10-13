package org.example.product;

import org.example.product.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

  private final Map<UUID, ProductDto> storage = new ConcurrentHashMap<>();

  public ProductService() {
    create(new ProductDto(null, "Galaxy Yarn", "Anti-gravity yarn ball", new BigDecimal("19.99"), "toys"));
    create(new ProductDto(null, "Comet Milk", "Fresh from the Oort cloud", new BigDecimal("7.49"), "food"));
  }

  public ProductDto create(ProductDto dto) {
    UUID id = Optional.ofNullable(dto.id()).orElse(UUID.randomUUID());
    ProductDto saved = new ProductDto(id, dto.name(), dto.description(), dto.price(), dto.category());
    storage.put(id, saved);
    return saved;
  }

  public List<ProductDto> findAll() {
    return new ArrayList<>(storage.values());
  }

  public Optional<ProductDto> findById(UUID id) {
    return Optional.ofNullable(storage.get(id));
  }

  public Optional<ProductDto> update(UUID id, ProductDto dto) {
    if (!storage.containsKey(id)) return Optional.empty();
    ProductDto updated = new ProductDto(id, dto.name(), dto.description(), dto.price(), dto.category());
    storage.put(id, updated);
    return Optional.of(updated);
  }

  public boolean delete(UUID id) {
    return storage.remove(id) != null;
  }

  public void deleteIfExists(UUID id) {
    storage.remove(id);
  }
}
