package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.InventoryRepository;
import spareparts.model.Inventory;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory createInventoryRecord(Inventory inventory) {
        if (inventory.getMovementDate() == null) {
            inventory.setMovementDate(LocalDateTime.now());
        }
        if (inventory.getStockInQuantity() == null) inventory.setStockInQuantity(0);
        if (inventory.getStockOutQuantity() == null) inventory.setStockOutQuantity(0);
        if (inventory.getReorderLevel() == null) inventory.setReorderLevel(5);

        // Auto-calculate current quantity: Stock In minus Stock Out
        if (inventory.getCurrentQuantity() == null || inventory.getCurrentQuantity() == 0) {
            inventory.setCurrentQuantity(inventory.getStockInQuantity() - inventory.getStockOutQuantity());
        }

        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventoryRecord(Long id, Inventory details) {
        Inventory existing = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory record not found"));
        if (details.getSparePartId() != null) existing.setSparePartId(details.getSparePartId());
        if (details.getCurrentQuantity() != null) existing.setCurrentQuantity(details.getCurrentQuantity());
        if (details.getStockInQuantity() != null) existing.setStockInQuantity(details.getStockInQuantity());
        if (details.getStockOutQuantity() != null) existing.setStockOutQuantity(details.getStockOutQuantity());
        if (details.getReorderLevel() != null) existing.setReorderLevel(details.getReorderLevel());
        if (details.getNotes() != null) existing.setNotes(details.getNotes());
        return inventoryRepository.save(existing);
    }

    public void deleteInventoryRecord(Long id) {
        inventoryRepository.deleteById(id);
    }
}
