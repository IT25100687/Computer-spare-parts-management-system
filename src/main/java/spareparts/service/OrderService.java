package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.OrderRepository;
import spareparts.repository.OrderItemRepository;
import spareparts.repository.SaleRepository;
import spareparts.model.Order;
import spareparts.model.Sale;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final SaleRepository saleRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, SaleRepository saleRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.saleRepository = saleRepository;
    }

    // --- VALIDATION METHOD ---
    private void validateOrderFields(Long customerId, Long sparePartId, Integer quantity, Double unitPrice, Double totalAmount) {
        if (customerId != null && customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be a positive integer.");
        }
        if (sparePartId != null && sparePartId <= 0) {
            throw new IllegalArgumentException("Spare Part ID must be a positive integer.");
        }
        if (quantity != null && quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (unitPrice != null && unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }
        if (totalAmount != null && totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative.");
        }
    }

    // --- CREATE ORDER ---
    public Order createOrder(Order order) {
        if (order.getCustomerId() == null || order.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Valid Customer ID is required.");
        }
        if (order.getSparePartId() == null || order.getSparePartId() <= 0) {
            throw new IllegalArgumentException("Valid Spare Part ID is required.");
        }
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        validateOrderFields(order.getCustomerId(), order.getSparePartId(), order.getQuantity(), order.getUnitPrice(), order.getTotalAmount());

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }
        if (order.getOrderStatus() == null || order.getOrderStatus().isEmpty()) {
            order.setOrderStatus("Pending");
        }
        if (order.getQuantity() != null && order.getUnitPrice() != null && (order.getTotalAmount() == null || order.getTotalAmount() == 0)) {
            order.setTotalAmount(order.getQuantity() * order.getUnitPrice());
        }
        return orderRepository.save(order);
    }

    // --- READ ORDERS ---
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // --- UPDATE ORDER ---
    public Order updateOrder(Long id, Order orderDetails) {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        validateOrderFields(orderDetails.getCustomerId(), orderDetails.getSparePartId(), orderDetails.getQuantity(), orderDetails.getUnitPrice(), orderDetails.getTotalAmount());
        if (orderDetails.getCustomerId() != null) existing.setCustomerId(orderDetails.getCustomerId());
        if (orderDetails.getSparePartId() != null) existing.setSparePartId(orderDetails.getSparePartId());
        if (orderDetails.getQuantity() != null) existing.setQuantity(orderDetails.getQuantity());
        if (orderDetails.getUnitPrice() != null) existing.setUnitPrice(orderDetails.getUnitPrice());
        if (orderDetails.getTotalAmount() != null) existing.setTotalAmount(orderDetails.getTotalAmount());
        if (orderDetails.getOrderStatus() != null) existing.setOrderStatus(orderDetails.getOrderStatus());
        return orderRepository.save(existing);
    }

    // --- DELETE / CANCEL ORDER ---
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // --- COMPLETE ORDER & GENERATE SALE ---
    public Sale completeOrderAndCreateSale(Long orderId, String paymentStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus("Completed");
        orderRepository.save(order);

        Sale sale = new Sale();
        sale.setOrderId(order.getOrderId());
        sale.setCustomerId(order.getCustomerId());
        sale.setSaleDate(LocalDateTime.now());
        sale.setPaymentStatus(paymentStatus != null ? paymentStatus : "Paid");
        sale.setInvoiceNumber("INV-" + System.currentTimeMillis());

        return saleRepository.save(sale);
    }

    // --- READ SALES ---
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    // --- UPDATE SALE PAYMENT STATUS ---
    public Sale updateSaleStatus(Long id, String paymentStatus) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setPaymentStatus(paymentStatus);
        return saleRepository.save(sale);
    }

    // --- DELETE / CANCEL SALE ---
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}