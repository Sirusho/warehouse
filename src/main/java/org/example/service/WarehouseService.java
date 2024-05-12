package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.dto.MaterialDto;
import org.example.dto.MaterialMoveDto;
import org.example.dto.WarehouseDto;
import org.example.mapper.MaterialMapper;
import org.example.mapper.WarehouseMapper;
import org.example.model.Material;
import org.example.model.Warehouse;
import org.example.repository.MaterialRepository;
import org.example.repository.WarehouseRepository;
import org.example.validation.MaterialExists;
import org.example.validation.WarehouseExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
@Log4j2
@RequiredArgsConstructor
public class WarehouseService {

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private WarehouseObserverManager warehouseObserver;

    @Transactional
    public WarehouseDto createWarehouse(@Valid WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        addMaterialsIfPresent(warehouseDto.getMaterialDtos(), warehouse);

        return persistAndReturn(warehouse);
    }

    @Transactional
    public WarehouseDto addMaterialToWarehouse(@WarehouseExists UUID warehouseId, MaterialDto materialDto) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();
        if (!CollectionUtils.isEmpty(warehouse.getMaterials())) {
            mergeWithExistingMaterials(materialDto, warehouse);
        } else {
            addMaterialIfValid(warehouse.getMaterials(), materialDto);
        }

        return persistAndReturn(warehouse);
    }

    @Transactional
    public void removeMaterial(@WarehouseExists UUID id, @MaterialExists UUID materialId) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();
        Set<Material> materials = warehouse.getMaterials().stream().filter(m -> m.getId().equals(materialId)).collect(Collectors.toSet());
        if (materials.contains(material)) {
            warehouse.getMaterials().remove(material);
            persistAndReturn(warehouse);
        } else {
            throw new RuntimeException("Material is not contained in the warehouse");
        }
    }

    @Transactional
    public void moveMaterial(@Valid MaterialMoveDto materialMoveDto) {
        Material materialToBeMoved = materialRepository.findById(materialMoveDto.getMaterialId()).orElseThrow();

        log.info("Removing material from warehouse with ID {}", materialMoveDto.getFromWarehouseId());
        removeMaterial(materialMoveDto.getFromWarehouseId(), materialMoveDto.getMaterialId());

        log.info("Adding material to warehouse with ID {}", materialMoveDto.getToWarehouseId());
        addMaterialToWarehouse(materialMoveDto.getToWarehouseId(), materialMapper.convert(materialToBeMoved));
    }

    @PostConstruct
    public void init() {
        warehouseObserver.addObserver(new WarehouseObserverImpl());
    }

    private void addMaterialIfValid(Set<Material> materials, MaterialDto materialDto) {
        if (materialDto.getMaterialTypeDto().getMaxCapacity() >= materialDto.getQuantity()) {
            materials.add(materialMapper.merge(materialDto));
        } else {
            log.warn("Material was not added to the warehouse as quantity of {} exceeded the max capacity of {}", materialDto.getQuantity(), materialDto.getMaterialTypeDto().getMaxCapacity());
            throw new RuntimeException("There is no much space in warehouse");
        }
    }

    private void addMaterialsIfPresent(Set<MaterialDto> materialDtos, Warehouse warehouse) {
        if (!CollectionUtils.isEmpty(materialDtos)) {
            for (MaterialDto materialDto : materialDtos) {
                addMaterialIfValid(warehouse.getMaterials(), materialDto);
            }
        }
    }

    private void mergeWithExistingMaterials(MaterialDto materialDto, Warehouse warehouse) {
        Optional<Material> existingMaterial = warehouse.getMaterials()
                .stream()
                .filter(m -> m.getMaterialType().getName()
                        .equals(materialDto.getMaterialTypeDto().getName()))
                .findFirst();
        if (existingMaterial.isEmpty()){
            addMaterialIfValid(warehouse.getMaterials(), materialDto);
        } else if(existingMaterial.get().getMaterialType().getMaxCapacity() - existingMaterial.get().getQuantity() >= materialDto.getQuantity()) {
            existingMaterial.get().setQuantity(materialDto.getQuantity() + existingMaterial.get().getQuantity());
        } else {
            throw new RuntimeException("The material max capacity does not allow " + materialDto.getQuantity() + " materials to be added");
        }
    }

    private WarehouseDto persistAndReturn(Warehouse warehouse) {
        warehouseRepository.saveAndFlush(warehouse);
        WarehouseDto warehouseConvertedDto = warehouseMapper.convert(warehouse);
        warehouseObserver.notifyObservers(warehouseConvertedDto);
        return warehouseConvertedDto;
    }

}
