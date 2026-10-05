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

    private void validateSupplierFields(String company, String supplierName, String email, String phone) {
        if (company != null) {
            if (company.trim().isEmpty()) {
                throw new IllegalArgumentException("Company name cannot be empty.");
            }
            if (company.matches("^\\d+$") || !company.matches(".*[a-zA-Z].*")) {
                throw new IllegalArgumentException("Company name must contain valid words/letters and cannot be purely numeric.");
            }
        }
        if (supplierName != null && !supplierName.trim().isEmpty()) {
            if (supplierName.matches("^\\d+$") || !supplierName.matches(".*[a-zA-Z].*")) {
                throw new IllegalArgumentException("Contact person name must contain valid words/letters and cannot be purely numeric.");
            }
        }
        if (phone != null && !phone.trim().isEmpty()) {
            String digits = phone.replaceAll("\\D", "");
            if (digits.length() != 10) {
                throw new IllegalArgumentException("Phone number must contain exactly 10 digits (e.g. 0771234567).");
            }
        }
        if (email != null && !email.trim().isEmpty() && !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
    }

    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getCompany() == null || supplier.getCompany().trim().isEmpty()) {
            throw new IllegalArgumentException("Company name is required.");
        }
        if (supplier.getPhone() == null || supplier.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier phone number is required.");
        }
        validateSupplierFields(supplier.getCompany(), supplier.getSupplierName(), supplier.getEmail(), supplier.getPhone());

        if (supplier.getStatus() == null || supplier.getStatus().isEmpty()) {
            supplier.setStatus("Active");
        }
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier details) {
        Supplier existing = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        validateSupplierFields(details.getCompany(), details.getSupplierName(), details.getEmail(), details.getPhone());

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
