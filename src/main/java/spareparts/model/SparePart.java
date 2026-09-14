package spareparts.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spare_parts")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sparePartId;

    private String partName;
    private String category;
    private String brand;
    private String description;
    private Double unitPrice;
    private String compatibility;
    private String warrantyEligibility; // Eligible (12 Months), Not Eligible
    private Integer reorderLevel;
    private Integer stockQuantity;
    private Long supplierId;

    public SparePart() {
    }

    public SparePart(Long sparePartId, String partName, String category, String brand, String description, Double unitPrice, String compatibility, String warrantyEligibility, Integer reorderLevel, Integer stockQuantity, Long supplierId) {
        this.sparePartId = sparePartId;
        this.partName = partName;
        this.category = category;
        this.brand = brand;
        this.description = description;
        this.unitPrice = unitPrice;
        this.compatibility = compatibility;
        this.warrantyEligibility = warrantyEligibility;
        this.reorderLevel = reorderLevel;
        this.stockQuantity = stockQuantity;
        this.supplierId = supplierId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getWarrantyEligibility() {
        return warrantyEligibility;
    }

    public void setWarrantyEligibility(String warrantyEligibility) {
        this.warrantyEligibility = warrantyEligibility;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
