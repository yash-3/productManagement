package com.ecommerce.productManagement.controller;

import com.ecommerce.productManagement.model.Product;
import com.ecommerce.productManagement.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains various test cases to check different scenarios for CRUD operations on product.
 */
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private final String productJson = """
            {
                "name": "Mobile",
                "price": 100.0,
                "description": "Mobile with good processor",
                "quantity": 10
            }
            """;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    /**
     * Test case for retrieving all products when there are products available.
     *
     * @throws Exception if any error occurs during request execution
     */
    @Test
    void testGetAllProducts_Success() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1L, "Mobile", 100.0, "Mobile with good processor", 10),
                new Product(2L, "Laptop", 1500.0, "High performance laptop", 5)
        );
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Mobile"))
                .andExpect(jsonPath("$[1].name").value("Laptop"));

        verify(productService, times(1)).getAllProducts();
    }

    /**
     * Test case for retrieving a product by ID when the product is found.
     *
     * @throws Exception if any error occurs during request execution
     */
    @Test
    void testGetProductById_ProductFound() throws Exception {
        Long productId = 1L;
        Product product = new Product(productId, "Mobile", 100.0, "Mobile with good processor", 10);
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Mobile"))
                .andExpect(jsonPath("$.price").value(100.0));

        verify(productService, times(1)).getProductById(productId);
    }

    /**
     * Test case for retrieving a product by ID when the product is not found.
     *
     * @throws Exception if any error occurs during request execution
     */
    @Test
    void testGetProductById_ProductNotFound() throws Exception {
        Long productId = 999L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isNotFound());
    }

    /**
     * Test case for create new product.
     * @throws Exception
     */
    @Test
    void createProduct() throws Exception {
        Product product = new Product(1L, "Mobile", 100.0, "Mobile with good processor", 10);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mobile"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.description").value("Mobile with good processor"))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    /**
     * Test case for updating a product when the product is found.
     * @throws Exception
     */
    @Test
    void updateProduct_Success() throws Exception {
        Product updatedProduct = new Product(1L, "Mobile", 100.0, "Mobile with good processor", 10);
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mobile"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.description").value("Mobile with good processor"))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }

    /**
     * Test case for updating a product when the product is not found.
     *
     * @throws Exception if any error occurs during request execution
     */
    @Test
    void testUpdateProduct_ProductNotFound() throws Exception {
        Long productId = 1L;
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(null);

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isNotFound());
    }

    /**
     * Test case for successful product deletion.
     * @throws Exception
     */
    @Test
    void testDeleteProduct_Success() throws Exception {
        Long productId = 1L;
        when(productService.deleteProduct(productId)).thenReturn(true);

        mockMvc.perform(delete("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(productService, times(1)).deleteProduct(1L);
    }

    /**
     * Test case for product deletion failure.
     * @throws Exception
     */
    @Test
    void testDeleteProduct_NotFound() throws Exception {
        Long productId = 1L;
        when(productService.deleteProduct(productId)).thenReturn(false);

        mockMvc.perform(delete("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}