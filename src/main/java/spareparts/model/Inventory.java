package spareparts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long sparePartId;
    private Integer currentQuantity;
    private Integer stockInQuantity;
    private Integer stockOutQuantity;
    private Integer reorderLevel;
    private LocalDateTime movementDate;
    private String notes;

    public Inventory() {
    }

    public Inventory(Long inventoryId, Long sparePartId, Integer currentQuantity, Integer stockInQuantity, Integer stockOutQuantity, Integer reorderLevel, LocalDateTime movementDate, String notes) {
        this.inventoryId = inventoryId;
        this.sparePartId = sparePartId;
        this.currentQuantity = currentQuantity;
        this.stockInQuantity = stockInQuantity;
        this.stockOutQuantity = stockOutQuantity;
        this.reorderLevel = reorderLevel;
        this.movementDate = movementDate;
        this.notes = notes;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(Long sparePartId) {
        this.sparePartId = sparePartId;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getStockInQuantity() {
        return stockInQuantity;
    }

    public void setStockInQuantity(Integer stockInQuantity) {
        this.stockInQuantity = stockInQuantity;
    }

    public Integer getStockOutQuantity() {
        return stockOutQuantity;
    }

    public void setStockOutQuantity(Integer stockOutQuantity) {
        this.stockOutQuantity = stockOutQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
