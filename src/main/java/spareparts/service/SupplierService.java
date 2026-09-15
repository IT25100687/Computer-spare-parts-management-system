package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.SupplierRepository;
import spareparts.model.Supplier;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getStatus() == null || supplier.getStatus().isEmpty()) {
            supplier.setStatus("Active");
        }
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier details) {
        Supplier existing = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        if (details.getSupplierName() != null) existing.setSupplierName(details.getSupplierName());
        if (details.getCompany() != null) existing.setCompany(details.getCompany());
        if (details.getPhone() != null) existing.setPhone(details.getPhone());
        if (details.getEmail() != null) existing.setEmail(details.getEmail());
        if (details.getAddress() != null) existing.setAddress(details.getAddress());
        if (details.getStatus() != null) existing.setStatus(details.getStatus());
        if (details.getPartsSupplied() != null) existing.setPartsSupplied(details.getPartsSupplied());
        return supplierRepository.save(existing);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
