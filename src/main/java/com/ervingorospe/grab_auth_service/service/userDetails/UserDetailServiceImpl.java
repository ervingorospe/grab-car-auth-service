package com.ervingorospe.grab_auth_service.service.userDetails;

import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import com.ervingorospe.grab_auth_service.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService{
    private final UserDetailsRepo repository;

    @Autowired
    public UserDetailServiceImpl(UserDetailsRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails createUserDetails(UserRegistrationDTO userRegistrationDTO, User user) {
        UserDetails userDetails = new UserDetails(userRegistrationDTO);
        userDetails.setUser(user);

        return repository.save(userDetails);
    }
}
