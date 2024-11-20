package com.ecommerce.productManagement.exception;

import com.ecommerce.productManagement.constants.ProductManagementConstants;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException() {
        super(ProductManagementConstants.PRODUCT_ALREADY_EXIST);
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}