package org.example.repository;

import org.example.model.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MaterialTypeRepository extends JpaRepository<MaterialType, UUID> {

    Optional<MaterialType> findByName(String name);
}
