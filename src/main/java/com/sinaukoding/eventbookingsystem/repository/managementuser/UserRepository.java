package com.sinaukoding.eventbookingsystem.repository.managementuser;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmailAndIdNot(String email, String id);
    Boolean existsByUsernameAndIdNot(String username, String id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String username);
}
