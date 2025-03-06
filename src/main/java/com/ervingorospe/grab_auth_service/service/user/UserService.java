package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;

public interface UserService {
    UserDTO createUser(User user);
}
