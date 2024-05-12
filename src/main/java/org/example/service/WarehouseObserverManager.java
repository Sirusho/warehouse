package org.example.service;

import org.example.dto.WarehouseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseObserverManager {
    private List<WarehouseObserver> observers = new ArrayList<>();

    public void addObserver(WarehouseObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WarehouseObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(WarehouseDto warehouseDto) {
        for (WarehouseObserver observer : observers) {
            observer.updateWarehouse(warehouseDto);
        }
    }
}
