package spareparts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_requests")
public class StockRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long sparePartId;
    private String partName;
    private String brand;
    private Integer currentStock;
    private Integer requestedQuantity;
    private String requestStatus; // Pending, Order Placed, Fulfilled
    private LocalDateTime requestDate;
    private String notes;

    public StockRequest() {
    }

    public StockRequest(Long requestId, Long sparePartId, String partName, String brand, Integer currentStock, Integer requestedQuantity, String requestStatus, LocalDateTime requestDate, String notes) {
        this.requestId = requestId;
        this.sparePartId = sparePartId;
        this.partName = partName;
        this.brand = brand;
        this.currentStock = currentStock;
        this.requestedQuantity = requestedQuantity;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.notes = notes;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(Long sparePartId) {
        this.sparePartId = sparePartId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
