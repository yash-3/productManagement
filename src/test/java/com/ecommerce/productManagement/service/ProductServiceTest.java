package com.ecommerce.productManagement.service;

import com.ecommerce.productManagement.exception.ProductNotFoundException;
import com.ecommerce.productManagement.model.Product;
import com.ecommerce.productManagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * This class test expected behavior of all methods of ProductService class.
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "Mobile", 100.0, "Mobile Description", 10);
    }

    /**
     * Test case for retrieving a product by ID when the product is found.
     */
    @Test
    public void testGetProductById_Found() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Mobile", result.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    /**
     * Test case for retrieving a product by ID when the product is not found.
     */
    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(1L);

        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }

    /**
     * Test case for retrieving all products when there are products available.
     */
    @Test
    public void testGetAllProducts() {
        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mobile", result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    /**
     * Test case for create new product.
     */
    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("Mobile", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    /**
     * Test case for update existing product when product updated successfully.
     */
    @Test
    public void testUpdateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        product.setName("Laptop");
        product.setPrice(120.0);

        Product result = productService.updateProduct(1L, product);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(120.0, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    /**
     * Test case for update existing product when product not found.
     */
    @Test
    public void testUpdateProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1L, product));
        verify(productRepository, times(1)).findById(1L);
    }

    /**
     * Test case for delete existing product when product deleted successfully.
     */
    @Test
    public void testDeleteProduct_Found() {
        when(productRepository.existsById(anyLong())).thenReturn(true);

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    /**
     * Test case for delete existing product when product not found.
     */
    @Test
    public void testDeleteProduct_NotFound() {
        when(productRepository.existsById(anyLong())).thenReturn(false);

        boolean result = productService.deleteProduct(1L);

        assertFalse(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(0)).deleteById(1L);
    }
}
