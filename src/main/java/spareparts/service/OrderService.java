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

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        order.setOrderStatus("Pending");
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // NEW LOGIC: Complete the order and generate a Sale (Invoice)
    public Sale completeOrderAndCreateSale(Long orderId, String paymentStatus) {
        // 1. Find the order
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // 2. Mark order as completed
        order.setOrderStatus("Completed");
        orderRepository.save(order);

        // 3. Create the official Sale record
        Sale sale = new Sale();
        sale.setOrderId(order.getOrderId());
        sale.setCustomerId(order.getCustomerId());
        sale.setSaleDate(LocalDateTime.now());
        sale.setPaymentStatus(paymentStatus);
        sale.setInvoiceNumber("INV-" + System.currentTimeMillis()); // Generate a basic invoice number

        return saleRepository.save(sale);
    }
}