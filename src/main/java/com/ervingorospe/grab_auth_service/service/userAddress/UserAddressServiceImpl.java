package com.ervingorospe.grab_auth_service.service.userAddress;

import com.ervingorospe.grab_auth_service.model.DTO.UserAddressDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserAddress;
import com.ervingorospe.grab_auth_service.repository.UserAddressRepo;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl implements UserAddressService{
    private final UserAddressRepo repository;

    public UserAddressServiceImpl(UserAddressRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserAddressDTO createAddress(UserRegistrationDTO userRegistrationDTO, User user) {
        UserAddress address = new UserAddress(userRegistrationDTO);
        address.setUser(user);
        UserAddress savedAddress = repository.save(address);

        return new UserAddressDTO(savedAddress);
    }
}
