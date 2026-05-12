package com.redhat.coolstore.inventory;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class InventoryRepository {
    private final Map<String, InventoryItem> inventory = new LinkedHashMap<>();

    public InventoryRepository() {
        add(new InventoryItem(1L, "329299", "Raleigh", 35, "http://redhat.com"));
        add(new InventoryItem(2L, "329199", "Raleigh", 12, "http://redhat.com"));
        add(new InventoryItem(3L, "165613", "Boston", 0, "http://redhat.com"));
    }

    public List<InventoryItem> list() {
        return List.copyOf(inventory.values());
    }

    public Optional<InventoryItem> findByItemId(String itemId) {
        return Optional.ofNullable(inventory.get(itemId));
    }

    private void add(InventoryItem item) {
        inventory.put(item.itemId(), item);
    }
}
