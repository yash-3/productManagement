package com.ecommerce.productManagement.service;

import com.ecommerce.productManagement.exception.ProductNotFoundException;
import com.ecommerce.productManagement.model.Product;
import com.ecommerce.productManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for handling business logic related to products.
 * It interacts with the DAO layer to perform CRUD operations (Create, Read, Update, Delete)
 * on product entities in the database.
 */
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a product by its ID.
     * @param id the unique identifier of the product to retrieve.
     * @return an Optional<Product> if found, or an empty {@link Optional} if not.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Retrieves all products in the system.
     *
     * @return a {@link List} of all {@link Product} objects.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Creates a new product in the database.
     *
     * @param product the product to create.
     * @return the {@link Product} object that was saved.
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product with the provided details.
     * 
     * @param id the unique identifier of the product to update.
     * @param product the product with updated information.
     * @return the updated {@link Product} object after saving it to the repository.
     */
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the unique identifier of the product to be deleted.
     * @return true if the product was deleted successfully, otherwise false.
     */
    public boolean deleteProduct(Long id) {
        // Check if the product exists or not
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}