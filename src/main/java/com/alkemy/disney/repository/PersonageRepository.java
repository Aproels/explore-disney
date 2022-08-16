package com.alkemy.disney.repository;


import com.alkemy.disney.entitys.EntityPersonage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonageRepository extends JpaRepository<EntityPersonage, Long>, JpaSpecificationExecutor<EntityPersonage> {
List<EntityPersonage> findAll(Specification<EntityPersonage> spec);
}
