package com.ecommerce.productManagement.model;

import com.ecommerce.productManagement.constants.ProductManagementConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = ProductManagementConstants.PRODUCT_REPRESENT)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = ProductManagementConstants.PRODUCT_UNIQUE_IDENTIFIER, example = "1")
    private Long id;

    @NotBlank(message = ProductManagementConstants.PRODUCT_NAME_REQUIRED)
    @Schema(description = ProductManagementConstants.PRODUCT_NAME_DESCRIPTION, example = "Mobile")
    private String name;

    @Positive(message = ProductManagementConstants.PRICE_MUST_BE_POSITIVE)
    @Schema(description = ProductManagementConstants.PRODUCT_PRICE_DESCRIPTION, example = "10000.00")
    private double price;

    @Schema(description = ProductManagementConstants.PRODUCT_DESCRIPTION_DESCRIPTION, example = "A mobile suitable for amazing photography")
    private String description;

    @Positive(message = ProductManagementConstants.QUANTITY_MUST_BE_POSITIVE)
    @Schema(description = ProductManagementConstants.PRODUCT_QUANTITY_DESCRIPTION, example = "10")
    private int quantity;
}