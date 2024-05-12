package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.dto.MaterialDto;
import org.example.dto.MaterialMoveDto;
import org.example.dto.WarehouseDto;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Log4j2
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/add")
    public ResponseEntity<WarehouseDto> createWarehouse(@RequestBody WarehouseDto warehouseDto) {
        log.info("Received to create a warehouse {}", warehouseDto);
        WarehouseDto warehouse = warehouseService.createWarehouse(warehouseDto);

        return ResponseEntity.ok(warehouse);
    }

    @PostMapping("/{warehouseId}/materials")
    public ResponseEntity<WarehouseDto> addMaterialToWarehouse(@PathVariable UUID warehouseId, @RequestBody MaterialDto materialDto) {
        log.info("Received to add material {} to warehouse with ID {}", materialDto,  warehouseId);
        WarehouseDto warehouseDto = warehouseService.addMaterialToWarehouse(warehouseId, materialDto);
        return ResponseEntity.ok(warehouseDto);
    }

    @PutMapping("/move")
    public ResponseEntity<Void> moveFromWarehouse(@RequestBody MaterialMoveDto materialMoveDto) {
        log.info("Received to move material from warehouse" + materialMoveDto);
        warehouseService.moveMaterial(materialMoveDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{warehouseId}/materials/{materialId}")
    public ResponseEntity<Void> removeMaterialFromWarehouse(@PathVariable UUID warehouseId, @PathVariable UUID materialId) {
        log.info("Received to remove material {} from warehouse {}", materialId, warehouseId);
        warehouseService.removeMaterial(warehouseId, materialId);
        return ResponseEntity.noContent().build();
    }

}
