# Coolstore Inventory Service

A Quarkus microservice that provides product inventory information for the
Coolstore retail domain: stock levels per store location and availability
lookups by item.

## Technology

| | |
|---|---|
| Framework | Red Hat build of Quarkus (`com.redhat.quarkus.platform` BOM 3.27.3.SP1) |
| Language | Java 21 |
| HTTP | Quarkus REST (Jakarta REST) with Jackson JSON |
| Observability | SmallRye Health, Micrometer with Prometheus registry |
| Container | UBI 9 OpenJDK 21 runtime (`Containerfile`) |

## API

Base path: `/api/inventory` — all responses are JSON.

| Method | Path | Description |
|---|---|---|
| GET | `/api/inventory` | List all inventory items |
| GET | `/api/inventory/{itemId}` | One item by its `itemId`; `404` if unknown |
| GET | `/api/inventory/{itemId}/availability` | Availability summary for an item; `404` if unknown |

### Resource shapes

Inventory item:

```json
{
  "id": 1,
  "itemId": "329299",
  "location": "Raleigh",
  "quantity": 35,
  "link": "http://redhat.com"
}
```

Availability (`available` is `true` when `quantity > 0`):

```json
{
  "itemId": "329299",
  "available": true,
  "quantity": 35
}
```

### Examples

```bash
curl -s http://localhost:8080/api/inventory | jq
curl -s http://localhost:8080/api/inventory/329299 | jq
curl -s http://localhost:8080/api/inventory/165613/availability | jq
```

## Running locally

Development mode with hot reload (serves on port `8080`):

```bash
./mvnw compile quarkus:dev
```

Run the test suite:

```bash
./mvnw test
```

Build the runnable package:

```bash
./mvnw package
```

## Health and metrics

| Endpoint | Purpose |
|---|---|
| `/q/health/live` | Liveness |
| `/q/health/ready` | Readiness |
| `/q/health` | Combined health report |
| `/q/metrics` | Prometheus metrics |

## Container image

```bash
./mvnw package
podman build -f Containerfile -t coolstore-inventory-service .
podman run --rm -p 8080:8080 coolstore-inventory-service
```

## Data

Inventory is held in an in-memory repository seeded at startup
(`InventoryRepository`). There is no external database; restarting the
service resets the data.

## Project layout

```
src/main/java/com/redhat/coolstore/inventory/   service source
src/test/java/com/redhat/coolstore/inventory/   JUnit 5 / RestAssured tests
src/main/resources/application.properties       runtime configuration
Containerfile                                   container build
devfile.yaml                                    cloud development workspace definition
catalog-info.yaml                               software catalog registration
```
