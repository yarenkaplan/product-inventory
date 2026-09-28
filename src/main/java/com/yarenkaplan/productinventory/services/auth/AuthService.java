package com.yarenkaplan.productinventory.services.auth;

import com.yarenkaplan.productinventory.requests.security.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
}
