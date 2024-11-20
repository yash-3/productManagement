package com.ecommerce.productManagement.service;

import com.ecommerce.productManagement.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Product Service, providing methods to manage products.
 */
public interface IProductService {

    /**
     * Retrieves a product by its unique ID.
     *
     * @param id the unique identifier of the product to retrieve.
     * @return an Optional containing the product if found, or an empty Optional if not found.
     */
    public Optional<Product> getProductById(Long id);

    /**
     * Retrieves all products from database.
     *
     * @return a list of all products.
     */
    public List<Product> getAllProducts();

    /**
     * Creates a new product in database.
     *
     * @param product the product to create.
     * @return the created product.
     */
    public Product createProduct(Product product);

    /**
     * Updates an existing product by its ID.
     *
     * @param id             the ID of the product to update.
     * @param updatedProduct the product with updated information.
     * @return the updated product.
     */
    public Product updateProduct(Long id, Product updatedProduct);

    /**
     * Deletes a product by its unique ID.
     *
     * @param id the unique identifier of the product to delete.
     * @return true if the product was successfully deleted, false otherwise.
     */
    public boolean deleteProduct(Long id);
}
