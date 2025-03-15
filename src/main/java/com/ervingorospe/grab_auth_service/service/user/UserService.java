package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;

public interface UserService {
    UserDTO createUser(UserRegistrationDTO user);
}
