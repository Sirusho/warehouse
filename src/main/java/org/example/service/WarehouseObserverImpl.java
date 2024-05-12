package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.dto.WarehouseDto;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class WarehouseObserverImpl implements WarehouseObserver {
    @Override
    public void updateWarehouse(WarehouseDto warehouseDto) {
        log.info("Warehouse updated: {}", warehouseDto);
    }
}