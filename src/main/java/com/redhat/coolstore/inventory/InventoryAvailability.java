package com.redhat.coolstore.inventory;

public record InventoryAvailability(String itemId, boolean available, int quantity) {
}
