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
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private String warrantyStatus; // Active, Expired, Void
    private String claimDescription;
    private String claimStatus; // No Claim, Claim Pending, Approved, Rejected, Resolved

    public Warranty() {
    }

    public Warranty(Long warrantyId, Long saleId, Long customerId, Long sparePartId, LocalDateTime startDate, LocalDateTime expiryDate, String warrantyStatus, String claimDescription, String claimStatus) {
        this.warrantyId = warrantyId;
        this.saleId = saleId;
        this.customerId = customerId;
        this.sparePartId = sparePartId;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.warrantyStatus = warrantyStatus;
        this.claimDescription = claimDescription;
        this.claimStatus = claimStatus;
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
}
