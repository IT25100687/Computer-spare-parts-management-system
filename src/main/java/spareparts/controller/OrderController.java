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

    // READ ALL
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // CREATE
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order #" + id + " cancelled/deleted successfully.";
    }

    // COMPLETE ORDER & GENERATE SALE INVOICE
    @PostMapping("/{id}/complete")
    public Sale completeOrder(@PathVariable Long id, @RequestParam(defaultValue = "Paid") String paymentStatus) {
        return orderService.completeOrderAndCreateSale(id, paymentStatus);
    }
}