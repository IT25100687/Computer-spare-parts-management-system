package spareparts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    private Long orderId;
    private Long customerId;
    private LocalDateTime saleDate;
    private String paymentStatus;
    private String invoiceNumber;

    public Sale() {
    }

    public Sale(Long saleId, Long orderId, Long customerId, LocalDateTime saleDate, String paymentStatus, String invoiceNumber) {
        this.saleId = saleId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.paymentStatus = paymentStatus;
        this.invoiceNumber = invoiceNumber;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setOrderDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}