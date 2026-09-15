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

    public SparePart createSparePart(SparePart sparePart) {
        if (sparePart.getReorderLevel() == null) {
            sparePart.setReorderLevel(5);
        }
        if (sparePart.getWarrantyEligibility() == null || sparePart.getWarrantyEligibility().isEmpty()) {
            sparePart.setWarrantyEligibility("Eligible (12 Months)");
        }
        return sparePartRepository.save(sparePart);
    }

    public SparePart updateSparePart(Long id, SparePart details) {
        SparePart existing = sparePartRepository.findById(id).orElseThrow(() -> new RuntimeException("Spare part not found"));
        if (details.getPartName() != null) existing.setPartName(details.getPartName());
        if (details.getCategory() != null) existing.setCategory(details.getCategory());
        if (details.getBrand() != null) existing.setBrand(details.getBrand());
        if (details.getDescription() != null) existing.setDescription(details.getDescription());
        if (details.getUnitPrice() != null) existing.setUnitPrice(details.getUnitPrice());
        if (details.getCompatibility() != null) existing.setCompatibility(details.getCompatibility());
        if (details.getWarrantyEligibility() != null) existing.setWarrantyEligibility(details.getWarrantyEligibility());
        if (details.getReorderLevel() != null) existing.setReorderLevel(details.getReorderLevel());
        if (details.getStockQuantity() != null) existing.setStockQuantity(details.getStockQuantity());
        if (details.getSupplierId() != null) existing.setSupplierId(details.getSupplierId());
        return sparePartRepository.save(existing);
    }

    public void deleteSparePart(Long id) {
        sparePartRepository.deleteById(id);
    }
}
