package com.ervingorospe.grab_auth_service.repository;

import com.ervingorospe.grab_auth_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
