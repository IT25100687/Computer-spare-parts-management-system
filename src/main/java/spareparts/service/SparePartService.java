package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.SparePartRepository;
import spareparts.model.SparePart;
import java.util.List;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;

    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    public List<SparePart> getAllSpareParts() {
        return sparePartRepository.findAll();
    }

    public SparePart getSparePartById(Long id) {
        return sparePartRepository.findById(id).orElse(null);
    }

    private void validateSparePartFields(Double unitPrice, Integer reorderLevel, Integer stockQuantity) {
        if (unitPrice != null && unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }
        if (reorderLevel != null && reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
        if (stockQuantity != null && stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
    }

    public SparePart createSparePart(SparePart sparePart) {
        if (sparePart.getPartName() == null || sparePart.getPartName().trim().isEmpty()) {
            throw new IllegalArgumentException("Part name is required.");
        }
        validateSparePartFields(sparePart.getUnitPrice(), sparePart.getReorderLevel(), sparePart.getStockQuantity());

        String cleanName = sparePart.getPartName().trim();
        List<SparePart> existingParts = sparePartRepository.findByPartNameIgnoreCase(cleanName);

        if (!existingParts.isEmpty()) {
            // Reuse existing SparePart ID for the same named spare part
            SparePart existing = existingParts.get(0);
            if (sparePart.getUnitPrice() != null) existing.setUnitPrice(sparePart.getUnitPrice());
            if (sparePart.getCategory() != null && !sparePart.getCategory().isEmpty()) existing.setCategory(sparePart.getCategory());
            if (sparePart.getBrand() != null && !sparePart.getBrand().isEmpty()) existing.setBrand(sparePart.getBrand());
            if (sparePart.getCompatibility() != null && !sparePart.getCompatibility().isEmpty()) existing.setCompatibility(sparePart.getCompatibility());
            if (sparePart.getWarrantyEligibility() != null && !sparePart.getWarrantyEligibility().isEmpty()) existing.setWarrantyEligibility(sparePart.getWarrantyEligibility());
            if (sparePart.getItemCondition() != null && !sparePart.getItemCondition().isEmpty()) existing.setItemCondition(sparePart.getItemCondition());
            if (sparePart.getDescription() != null && !sparePart.getDescription().isEmpty()) existing.setDescription(sparePart.getDescription());
            if (sparePart.getReorderLevel() != null) existing.setReorderLevel(sparePart.getReorderLevel());
            if (sparePart.getSupplierId() != null) existing.setSupplierId(sparePart.getSupplierId());
            
            // Increment existing stock quantity
            int additionalQty = (sparePart.getStockQuantity() != null && sparePart.getStockQuantity() > 0) ? sparePart.getStockQuantity() : 1;
            int currentQty = (existing.getStockQuantity() != null) ? existing.getStockQuantity() : 0;
            existing.setStockQuantity(currentQty + additionalQty);

            return sparePartRepository.save(existing);
        }

        if (sparePart.getReorderLevel() == null) {
            sparePart.setReorderLevel(5);
        }
        if (sparePart.getWarrantyEligibility() == null || sparePart.getWarrantyEligibility().isEmpty()) {
            sparePart.setWarrantyEligibility("Eligible (12 Months)");
        }
        if (sparePart.getItemCondition() == null || sparePart.getItemCondition().isEmpty()) {
            sparePart.setItemCondition("Brand New");
        }
        if (sparePart.getStockQuantity() == null) {
            sparePart.setStockQuantity(1);
        }
        return sparePartRepository.save(sparePart);
    }

    public SparePart updateSparePart(Long id, SparePart details) {
        SparePart existing = sparePartRepository.findById(id).orElseThrow(() -> new RuntimeException("Spare part not found"));
        validateSparePartFields(details.getUnitPrice(), details.getReorderLevel(), details.getStockQuantity());

        if (details.getPartName() != null) existing.setPartName(details.getPartName());
        if (details.getCategory() != null) existing.setCategory(details.getCategory());
        if (details.getBrand() != null) existing.setBrand(details.getBrand());
        if (details.getDescription() != null) existing.setDescription(details.getDescription());
        if (details.getUnitPrice() != null) existing.setUnitPrice(details.getUnitPrice());
        if (details.getCompatibility() != null) existing.setCompatibility(details.getCompatibility());
        if (details.getWarrantyEligibility() != null) existing.setWarrantyEligibility(details.getWarrantyEligibility());
        if (details.getItemCondition() != null) existing.setItemCondition(details.getItemCondition());
        if (details.getReorderLevel() != null) existing.setReorderLevel(details.getReorderLevel());
        if (details.getStockQuantity() != null) existing.setStockQuantity(details.getStockQuantity());
        if (details.getSupplierId() != null) existing.setSupplierId(details.getSupplierId());
        return sparePartRepository.save(existing);
    }

    public void deleteSparePart(Long id) {
        sparePartRepository.deleteById(id);
    }
}
