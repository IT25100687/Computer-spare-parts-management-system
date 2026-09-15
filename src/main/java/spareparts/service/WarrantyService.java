package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.WarrantyRepository;
import spareparts.model.Warranty;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public WarrantyService(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Warranty getWarrantyById(Long id) {
        return warrantyRepository.findById(id).orElse(null);
    }

    public Warranty createWarranty(Warranty warranty) {
        if (warranty.getStartDate() == null) {
            warranty.setStartDate(LocalDateTime.now());
        }
        if (warranty.getExpiryDate() == null) {
            // Default 1-year warranty coverage
            warranty.setExpiryDate(warranty.getStartDate().plusYears(1));
        }
        if (warranty.getWarrantyStatus() == null || warranty.getWarrantyStatus().isEmpty()) {
            warranty.setWarrantyStatus("Active");
        }
        if (warranty.getClaimStatus() == null || warranty.getClaimStatus().isEmpty()) {
            warranty.setClaimStatus("No Claim");
        }
        return warrantyRepository.save(warranty);
    }

    public Warranty updateWarranty(Long id, Warranty details) {
        Warranty existing = warrantyRepository.findById(id).orElseThrow(() -> new RuntimeException("Warranty record not found"));
        if (details.getSaleId() != null) existing.setSaleId(details.getSaleId());
        if (details.getCustomerId() != null) existing.setCustomerId(details.getCustomerId());
        if (details.getSparePartId() != null) existing.setSparePartId(details.getSparePartId());
        if (details.getWarrantyStatus() != null) existing.setWarrantyStatus(details.getWarrantyStatus());
        if (details.getClaimDescription() != null) existing.setClaimDescription(details.getClaimDescription());
        if (details.getClaimStatus() != null) existing.setClaimStatus(details.getClaimStatus());
        return warrantyRepository.save(existing);
    }

    public void deleteWarranty(Long id) {
        warrantyRepository.deleteById(id);
    }
}
