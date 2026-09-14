package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.service.InventoryService;
import spareparts.model.Inventory;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-records")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public Inventory createInventoryRecord(@RequestBody Inventory inventory) {
        return inventoryService.createInventoryRecord(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventoryRecord(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventoryRecord(id, inventory);
    }

    @DeleteMapping("/{id}")
    public String deleteInventoryRecord(@PathVariable Long id) {
        inventoryService.deleteInventoryRecord(id);
        return "Inventory Record #" + id + " removed successfully.";
    }
}
