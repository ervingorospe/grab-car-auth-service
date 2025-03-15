package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.handler.error.UserAlreadyExistsException;
import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import com.ervingorospe.grab_auth_service.repository.UserDetailsRepo;
import com.ervingorospe.grab_auth_service.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepository;
    private UserDetailsRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepository, UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsRepo = userDetailsRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional // use to make sure user and userDetails save together or fail together.
    public UserDTO createUser(UserRegistrationDTO userDTO) {
        Optional<User> findUser = userRepository.findByEmail(userDTO.getEmail());

        if (findUser.isPresent()) {
            throw new UserAlreadyExistsException("Your email is already registered.");
        }

        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            User savedUser = userRepository.save(user);
            UserDetails userDetails = new UserDetails(userDTO);
            userDetails.setUser(savedUser);
            UserDetails savedUserDetails = userDetailsRepo.save(userDetails);

            return new UserDTO(savedUser, savedUserDetails);
        } catch (RuntimeException e) {
            throw new RuntimeException("User registration failed");
        }
    }
}
