package com.redhat.coolstore.inventory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

@Path("/api/inventory")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {
    private static final Logger LOG = Logger.getLogger(InventoryResource.class);

    private final InventoryRepository repository;

    public InventoryResource(InventoryRepository repository) {
        this.repository = repository;
    }

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

    @GET
    @Path("/stats")
    public Map<String, Object> stats() {
        LOG.info("Incoming request: GET /api/inventory/stats");
        Map<String, Object> result = new HashMap<>();
        try {
            List<InventoryItem> items = repository.list();
            int totalCount = items.size();
            Map<String, Integer> byLocation = new HashMap<>();
            int inStock = 0;
            int outOfStock = 0;

            for (InventoryItem item : items) {
                byLocation.merge(item.location(), 1, Integer::sum);
                if (item.quantity() > 0) {
                    inStock++;
                } else {
                    outOfStock++;
                }
            }

            result.put("totalCount", totalCount);
            result.put("byLocation", byLocation);
            result.put("inStock", inStock);
            result.put("outOfStock", outOfStock);

            LOG.infof("Computed statistics: %s", result);
        } catch (Exception e) {
            LOG.error("Failed to compute inventory statistics", e);
        }
        return result;
    }

    private InventoryItem requireItem(String itemId) {
        return repository.findByItemId(itemId)
                .orElseThrow(() -> {
                    LOG.infof("Inventory lookup miss for itemId %s", itemId);
                    return new NotFoundException("No inventory item found for itemId " + itemId);
                });
    }
}
