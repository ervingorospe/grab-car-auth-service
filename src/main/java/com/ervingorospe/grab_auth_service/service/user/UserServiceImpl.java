package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.handler.error.UserAlreadyExistsException;
import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import com.ervingorospe.grab_auth_service.repository.UserRepo;
import com.ervingorospe.grab_auth_service.service.userAddress.UserAddressService;
import com.ervingorospe.grab_auth_service.service.userDetails.UserDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo repository;
    private final UserDetailService userDetailService;
    private final UserAddressService userAddressService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo repository, UserDetailService userDetailService, UserAddressService userAddressService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userDetailService = userDetailService;
        this.userAddressService = userAddressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional // use to make sure user and userDetails save together or fail together.
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        Optional<User> findUser = repository.findByEmail(userRegistrationDTO.email());

        if (findUser.isPresent()) {
            throw new UserAlreadyExistsException("Your email is already registered.");
        }

        User user = new User(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            User savedUser = repository.save(user);
            UserDetails userDetails = userDetailService.createUserDetails(userRegistrationDTO, user);
            userAddressService.createAddress(userRegistrationDTO, user);

            return new UserDTO(savedUser, userDetails);
        } catch (RuntimeException e) {
            throw new RuntimeException("User registration failed");
        }
    }
}
