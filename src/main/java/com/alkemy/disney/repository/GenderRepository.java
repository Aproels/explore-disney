package com.alkemy.disney.repository;

import com.alkemy.disney.entitys.EntityGender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<EntityGender, Long> {
}
