package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.service.OrderService;
import spareparts.model.Order;
import spareparts.model.Sale;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // NEW ENDPOINT: Complete an order and generate a sale
    @PostMapping("/{id}/complete")
    public Sale completeOrder(@PathVariable Long id, @RequestParam String paymentStatus) {
        return orderService.completeOrderAndCreateSale(id, paymentStatus);
    }
}