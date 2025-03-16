package com.ervingorospe.grab_auth_service.service.userAddress;

import com.ervingorospe.grab_auth_service.model.DTO.UserAddressDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;

public interface UserAddressService {
    UserAddressDTO createAddress(UserRegistrationDTO userRegistrationDTO, User user);
}
