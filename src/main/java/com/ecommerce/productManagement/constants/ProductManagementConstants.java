package com.ecommerce.productManagement.constants;

public final class ProductManagementConstants {

    private ProductManagementConstants() {}

    // REST API constants
    public static final String PRODUCT = "/products";
    public static final String GET_ALL = "/getAll";



    // Error messages
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_REPRESENT = "Represents a product in the system";
    public static final String PRODUCT_UNIQUE_IDENTIFIER = "The unique identifier of the product";
    public static final String PRODUCT_ALREADY_EXIST = "Product already exist.";
    public static final String PRODUCT_NAME_REQUIRED = "Product name is mandatory";
    public static final String PRICE_MUST_BE_POSITIVE = "Price must be a positive value";
    public static final String QUANTITY_MUST_BE_POSITIVE = "Quantity must be a positive value";
    
    // Swagger description constants
    public static final String PRODUCT_NAME_DESCRIPTION = "The name of the product";
    public static final String PRODUCT_PRICE_DESCRIPTION = "The price of the product";
    public static final String PRODUCT_QUANTITY_DESCRIPTION = "The available stock quantity of the product";
    public static final String PRODUCT_DESCRIPTION_DESCRIPTION = "A brief description of the product";

    // Other constants for configuration can be added here
    public static final String API_VERSION = "v1";
    public static final String API_BASE_PATH = "/api/products";
}