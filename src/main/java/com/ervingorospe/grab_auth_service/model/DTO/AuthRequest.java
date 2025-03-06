package com.ervingorospe.grab_auth_service.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    private String email;
    private String password;
}
