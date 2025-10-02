package org.example.product.dto;

import jakarta.validation.constraints.*;
import org.example.validation.CosmicWordCheck;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
    UUID id,

    @NotBlank
    @Size(min = 3, max = 50)
    @CosmicWordCheck
    String name,

    @Size(max = 255)
    String description,

    @NotNull
    @Positive
    BigDecimal price,

    @NotBlank
    @Size(min = 3, max = 30)
    String category
) {}
