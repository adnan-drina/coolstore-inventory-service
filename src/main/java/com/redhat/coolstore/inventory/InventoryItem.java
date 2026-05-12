package com.redhat.coolstore.inventory;

public record InventoryItem(Long id, String itemId, String location, int quantity, String link) {
}
