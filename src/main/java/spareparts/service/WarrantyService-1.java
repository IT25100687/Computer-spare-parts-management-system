package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.WarrantyRepository;

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
        List<Warranty> list = createWarranties(warranty);
        return list.get(0);
    }

    public List<Warranty> createWarranties(Warranty template) {
        int qty = (template.getItemQuantity() != null && template.getItemQuantity() > 0) ? template.getItemQuantity() : 1;
        List<Warranty> createdList = new java.util.ArrayList<>();

        for (int i = 1; i <= qty; i++) {
            Warranty w = new Warranty();
            w.setSaleId(template.getSaleId());
            w.setCustomerId(template.getCustomerId());
            w.setSparePartId(template.getSparePartId());
            w.setSupplierId(template.getSupplierId());

            LocalDateTime start = template.getStartDate() != null ? template.getStartDate() : LocalDateTime.now();
            w.setStartDate(start);

            int periodYears = (template.getWarrantyPeriodYears() != null && template.getWarrantyPeriodYears() > 0) ? template.getWarrantyPeriodYears() : 1;
            w.setWarrantyPeriodYears(periodYears);
            w.setExpiryDate(start.plusYears(periodYears));

            w.setWarrantyStatus(template.getWarrantyStatus() != null && !template.getWarrantyStatus().isEmpty() ? template.getWarrantyStatus() : "Active");
            w.setClaimStatus(template.getClaimStatus() != null && !template.getClaimStatus().isEmpty() ? template.getClaimStatus() : "No Claim");
            w.setClaimDescription(template.getClaimDescription());

            w.setItemQuantity(qty);
            w.setUnitNumber(i);

            createdList.add(warrantyRepository.save(w));
        }
        return createdList;
    }

    public Warranty updateWarranty(Long id, Warranty details) {
        Warranty existing = warrantyRepository.findById(id).orElseThrow(() -> new RuntimeException("Warranty record not found"));
        if (details.getSaleId() != null) existing.setSaleId(details.getSaleId());
        if (details.getCustomerId() != null) existing.setCustomerId(details.getCustomerId());
        if (details.getSparePartId() != null) existing.setSparePartId(details.getSparePartId());
        if (details.getSupplierId() != null) existing.setSupplierId(details.getSupplierId());
        if (details.getWarrantyPeriodYears() != null) existing.setWarrantyPeriodYears(details.getWarrantyPeriodYears());
        if (details.getStartDate() != null) existing.setStartDate(details.getStartDate());
        if (details.getExpiryDate() != null) existing.setExpiryDate(details.getExpiryDate());
        if (details.getWarrantyStatus() != null) existing.setWarrantyStatus(details.getWarrantyStatus());
        if (details.getClaimDescription() != null) existing.setClaimDescription(details.getClaimDescription());
        if (details.getClaimStatus() != null) existing.setClaimStatus(details.getClaimStatus());
        return warrantyRepository.save(existing);
    }

    public void deleteWarranty(Long id) {
        warrantyRepository.deleteById(id);
    }
}
