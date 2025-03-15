package com.ervingorospe.grab_auth_service.repository;

import com.ervingorospe.grab_auth_service.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailsRepo extends JpaRepository<UserDetails, UUID> {
}
