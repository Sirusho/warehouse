package org.example.repository;

import org.example.dto.MaterialDto;
import org.example.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, UUID> {

    Optional<Material> findByMaterialTypeName(String materialTypeName);
}
