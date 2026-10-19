package com.yarenkaplan.productinventory.errors;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("Username not found: "+ username);
    }
}
