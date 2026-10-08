package com.yarenkaplan.productinventory.errors;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access denied!");
    }
}
