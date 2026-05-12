package com.redhat.coolstore.inventory;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/inventory")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {
    @Inject
    InventoryRepository repository;

    @GET
    public List<InventoryItem> list() {
        return repository.list();
    }

    @GET
    @Path("/{itemId}")
    public InventoryItem get(@PathParam("itemId") String itemId) {
        return requireItem(itemId);
    }

    @GET
    @Path("/{itemId}/availability")
    public InventoryAvailability availability(@PathParam("itemId") String itemId) {
        InventoryItem item = requireItem(itemId);
        return new InventoryAvailability(item.itemId(), item.quantity() > 0, item.quantity());
    }

    private InventoryItem requireItem(String itemId) {
        return repository.findByItemId(itemId)
                .orElseThrow(() -> new NotFoundException("No inventory item found for itemId " + itemId));
    }
}
