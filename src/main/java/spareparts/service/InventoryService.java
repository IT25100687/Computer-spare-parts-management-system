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

    private void validateInventoryFields(Long sparePartId, Integer currentQuantity, Integer stockInQuantity, Integer stockOutQuantity, Integer reorderLevel) {
        if (sparePartId != null && sparePartId <= 0) {
            throw new IllegalArgumentException("Spare Part ID must be a positive integer.");
        }
        if (currentQuantity != null && currentQuantity < 0) {
            throw new IllegalArgumentException("Current quantity cannot be negative.");
        }
        if (stockInQuantity != null && stockInQuantity < 0) {
            throw new IllegalArgumentException("Stock-In quantity cannot be negative.");
        }
        if (stockOutQuantity != null && stockOutQuantity < 0) {
            throw new IllegalArgumentException("Stock-Out quantity cannot be negative.");
        }
        if (reorderLevel != null && reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
    }

    public Inventory createInventoryRecord(Inventory inventory) {
        if (inventory.getSparePartId() == null || inventory.getSparePartId() <= 0) {
            throw new IllegalArgumentException("Valid Spare Part ID is required.");
        }
        validateInventoryFields(inventory.getSparePartId(), inventory.getCurrentQuantity(), inventory.getStockInQuantity(), inventory.getStockOutQuantity(), inventory.getReorderLevel());

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

        if (inventory.getCurrentQuantity() < 0) {
            throw new IllegalArgumentException("Current quantity cannot be negative.");
        }

        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventoryRecord(Long id, Inventory details) {
        Inventory existing = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory record not found"));
        validateInventoryFields(details.getSparePartId(), details.getCurrentQuantity(), details.getStockInQuantity(), details.getStockOutQuantity(), details.getReorderLevel());

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
