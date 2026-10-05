package spareparts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranties")
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warrantyId;

    private Long saleId;
    private Long customerId;
    private Long sparePartId;
    private Long supplierId;
    private Integer warrantyPeriodYears;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private String warrantyStatus; // Active, Void
    private String claimDescription;
    private String claimStatus; // No Claim, Claimed, Claim Pending, Approved, Rejected, Resolved
    private Integer itemQuantity; // Total quantity of items bought in the sale
    private Integer unitNumber; // Specific unit number (e.g. 1 of 2, 2 of 2)

    public Warranty() {
    }

    public Warranty(Long warrantyId, Long saleId, Long customerId, Long sparePartId, Long supplierId, Integer warrantyPeriodYears, LocalDateTime startDate, LocalDateTime expiryDate, String warrantyStatus, String claimDescription, String claimStatus) {
        this.warrantyId = warrantyId;
        this.saleId = saleId;
        this.customerId = customerId;
        this.sparePartId = sparePartId;
        this.supplierId = supplierId;
        this.warrantyPeriodYears = warrantyPeriodYears;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.warrantyStatus = warrantyStatus;
        this.claimDescription = claimDescription;
        this.claimStatus = claimStatus;
        this.itemQuantity = 1;
        this.unitNumber = 1;
    }

    public Warranty(Long warrantyId, Long saleId, Long customerId, Long sparePartId, Long supplierId, Integer warrantyPeriodYears, LocalDateTime startDate, LocalDateTime expiryDate, String warrantyStatus, String claimDescription, String claimStatus, Integer itemQuantity, Integer unitNumber) {
        this.warrantyId = warrantyId;
        this.saleId = saleId;
        this.customerId = customerId;
        this.sparePartId = sparePartId;
        this.supplierId = supplierId;
        this.warrantyPeriodYears = warrantyPeriodYears;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.warrantyStatus = warrantyStatus;
        this.claimDescription = claimDescription;
        this.claimStatus = claimStatus;
        this.itemQuantity = itemQuantity;
        this.unitNumber = unitNumber;
    }

    public Long getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(Long warrantyId) {
        this.warrantyId = warrantyId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(Long sparePartId) {
        this.sparePartId = sparePartId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getWarrantyPeriodYears() {
        return warrantyPeriodYears;
    }

    public void setWarrantyPeriodYears(Integer warrantyPeriodYears) {
        this.warrantyPeriodYears = warrantyPeriodYears;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public Integer getItemQuantity() {
        return itemQuantity != null && itemQuantity > 0 ? itemQuantity : 1;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Integer getUnitNumber() {
        return unitNumber != null && unitNumber > 0 ? unitNumber : 1;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }
}
