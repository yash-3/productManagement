package com.ecommerce.productManagement.controller;

import com.ecommerce.productManagement.constants.ProductManagementConstants;
import com.ecommerce.productManagement.model.Product;
import com.ecommerce.productManagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class handles HTTP requests related to product management.
 * It provides endpoints to create, retrieve, update, and delete products.
 */
@Tag(
        name = "REST APIs for Product Management",
        description = "APIs on Product to CREATE, FETCH, UPDATE AND DELETE product details"
)
@RestController
@RequestMapping(value = ProductManagementConstants.PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Handles the GET request to retrieve all products.
     *
     * @return a list of {@link Product} objects representing all products in the system
     */
    @Operation(summary = "Get all products", description = "Retrieve a list of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products"),
            @ApiResponse(responseCode = "500", description = "Any Error occurred while retrieving")
    })
    @GetMapping (value = ProductManagementConstants.GET_ALL)
    public List<Product> getAllProducts() {
        log.debug("Enter in getAllProducts()");
        return productService.getAllProducts();
    }

    /**
     * Handles the GET request to retrieve a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return a {@link ResponseEntity} containing the found {@link Product} or a 404 Not Found response
     */
    @Operation(summary = "Get a product by ID", description = "Retrieve product details by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.debug("Enter in getProductById() with id: {}", id);
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles the POST request to create a new product.
     *
     * @param product the {@link Product} object to create
     * @return a {@link ResponseEntity} with the created product and HTTP status 201 Created
     */
    @Operation(summary = "Create a new product", description = "Add a new product to database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the product"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        log.debug("Enter in createProduct() with details: {}", product);
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Handles the PUT request to update an existing product.
     *
     * @param id the ID of the product to update
     * @param product the {@link Product} object containing updated details
     * @return a {@link ResponseEntity} with the updated product or a 404 Not Found response if not found
     */
    @Operation(summary = "Update an existing product", description = "Update the details of an existing product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the product"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        log.debug("Enter in updateProduct() with id: {}, details: {}", id, product);
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    /**
     * Handles the DELETE request to delete a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a HTTP status 204 if deletion is successful,
     * or a 404 Not Found if the product does not exist
     */
    @Operation(summary = "Delete a product", description = "Remove a product from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the product"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Enter in deleteProduct() with id: {}", id);
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
