package com.sinaukoding.eventbookingsystem.repository.managementuser;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
