package org.example.service;

import org.example.dto.WarehouseDto;

public interface WarehouseObserver {
    void updateWarehouse(WarehouseDto warehouseDto);
}