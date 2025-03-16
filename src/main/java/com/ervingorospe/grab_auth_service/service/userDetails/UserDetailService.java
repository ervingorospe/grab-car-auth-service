package com.ervingorospe.grab_auth_service.service.userDetails;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;

public interface UserDetailService {
    UserDetails createUserDetails(UserRegistrationDTO userRegistrationDTO, User user);
}
