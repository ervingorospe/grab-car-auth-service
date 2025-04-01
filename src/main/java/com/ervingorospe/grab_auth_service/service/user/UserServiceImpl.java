package com.ervingorospe.grab_auth_service.service.user;

import com.ervingorospe.grab_auth_service.handler.error.UserAlreadyExistsException;
import com.ervingorospe.grab_auth_service.handler.error.UserNotFoundException;
import com.ervingorospe.grab_auth_service.model.DTO.EmailDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserDTO;
import com.ervingorospe.grab_auth_service.model.DTO.UserRegistrationDTO;
import com.ervingorospe.grab_auth_service.model.entity.User;
import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import com.ervingorospe.grab_auth_service.repository.UserRepo;
import com.ervingorospe.grab_auth_service.service.kafka.KafkaProducerService;
import com.ervingorospe.grab_auth_service.service.userDetails.UserDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo repository;
    private final UserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public UserServiceImpl(UserRepo repository, UserDetailService userDetailService, PasswordEncoder passwordEncoder, KafkaProducerService kafkaProducerService) {
        this.repository = repository;
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
        this.kafkaProducerService = kafkaProducerService;
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

            // sending email
            kafkaProducerService.sendVerificationEmail(savedUser.getId(), "registration");

            return new UserDTO(savedUser, userDetails);
        } catch (RuntimeException e) {
            throw new RuntimeException("User registration failed");
        }
    }

    @Override
    @Transactional
    public String updateEmail(EmailDTO emailDTO, UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with email: " + id + " NOT FOUND"));

        try {
            user.setActive(false);
            user.setEmail(emailDTO.email());
            User savedUser = repository.save(user);

            // sending email
            kafkaProducerService.sendVerificationEmail(savedUser.getId(), "registration");

            return "Email Updated";
        } catch (RuntimeException e) {
            throw new RuntimeException("Changing Email failed");
        }

    }
}
