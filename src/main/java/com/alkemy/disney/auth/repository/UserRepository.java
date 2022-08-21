package com.alkemy.disney.auth.repository;

import com.alkemy.disney.auth.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EntityUser, Long> {
    EntityUser findByUsername(String username);
}
