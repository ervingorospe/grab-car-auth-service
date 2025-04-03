package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.model.DTO.EmailDTO;
import com.ervingorospe.grab_auth_service.model.DTO.PasswordDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;

import java.util.UUID;

public interface UserService {
    UserDTO createUser(UserRegistrationDTO user);
    String updateEmail(EmailDTO emailDTO, UUID id);
    String updatePassword(PasswordDTO passwordDTO, UUID id);
}
