package com.redhat.coolstore.inventory;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class InventoryResourceTest {
    @Test
    void listInventoryItems() {
        given()
                .when().get("/api/inventory")
                .then()
                .statusCode(200)
                .body("itemId", contains("329299", "329199", "165613"))
                .body("$", hasSize(3));
    }

    @Test
    void getInventoryItemByItemId() {
        given()
                .when().get("/api/inventory/329299")
                .then()
                .statusCode(200)
                .body("itemId", is("329299"))
                .body("location", is("Raleigh"))
                .body("quantity", is(35));
    }

    @Test
    void getInventoryAvailability() {
        given()
                .when().get("/api/inventory/329299/availability")
                .then()
                .statusCode(200)
                .body("itemId", is("329299"))
                .body("available", is(true))
                .body("quantity", is(35));
    }

    @Test
    void getOutOfStockInventoryAvailability() {
        given()
                .when().get("/api/inventory/165613/availability")
                .then()
                .statusCode(200)
                .body("itemId", is("165613"))
                .body("available", is(false))
                .body("quantity", is(0));
    }

    @Test
    void missingInventoryItemReturnsNotFound() {
        given()
                .when().get("/api/inventory/000000")
                .then()
                .statusCode(404);
    }
}
