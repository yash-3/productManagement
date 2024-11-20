package com.ecommerce.productManagement.exception;

import com.ecommerce.productManagement.constants.ProductManagementConstants;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super(ProductManagementConstants.PRODUCT_NOT_FOUND);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}