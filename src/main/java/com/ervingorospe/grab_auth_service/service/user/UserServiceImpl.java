package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.handler.error.UserAlreadyExistsException;
import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(User user) {
        Optional<User> findUser = repository.findByEmail(user.getEmail());

        if (findUser.isPresent()) {
            throw new UserAlreadyExistsException("Your email is already registered.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = Optional.of(repository.save(user))
                .orElseThrow(() -> new RuntimeException("User registration failed"));

        return new UserDTO(savedUser);
    }
}
