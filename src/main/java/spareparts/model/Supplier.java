package spareparts.model;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String supplierName;
    private String company;
    private String phone;
    private String email;
    private String address;
    private String status; // Active, Inactive
    private String partsSupplied;

    public Supplier() {
    }

    public Supplier(Long supplierId, String supplierName, String company, String phone, String email, String address, String status, String partsSupplied) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.partsSupplied = partsSupplied;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPartsSupplied() {
        return partsSupplied;
    }

    public void setPartsSupplied(String partsSupplied) {
        this.partsSupplied = partsSupplied;
    }
}
