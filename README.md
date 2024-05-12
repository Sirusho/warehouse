# Warehouse API

## Introduction
This API provides endpoints for managing warehouses and materials.

## Base URL
http://localhost:8082/api/warehouse

## Endpoints

### Create Warehouse
- **URL:** `POST /add`
- **Description:** Creates a new warehouse.
- **Request Body:** WarehouseDto
- **Response:** WarehouseDto

### Add Material to Warehouse
- **URL:** `POST /{warehouseId}/materials`
- **Description:** Adds a material to the specified warehouse.
- **Path Parameters:** `warehouseId` (UUID)
- **Request Body:** MaterialDto
- **Response:** WarehouseDto

### Move Material
- **URL:** `PUT /move`
- **Description:** Moves a material from one warehouse to another.
- **Request Body:** MaterialMoveDto
- **Response:** None (HTTP 200 on success)

### Remove Material from Warehouse
- **URL:** `DELETE /{warehouseId}/materials/{materialId}`
- **Description:** Removes a material from the specified warehouse.
- **Path Parameters:** `warehouseId` (UUID), `materialId` (UUID)
- **Response:** None (HTTP 204 on success)

## Swagger Documentation
You can explore and test the API using Swagger UI:
[Swagger UI](http://localhost:8082/api/warehouse/swagger-ui/index.html#/)

