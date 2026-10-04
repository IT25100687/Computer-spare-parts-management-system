package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.service.OrderService;
import spareparts.model.Sale;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final OrderService orderService;

    public SaleController(OrderService orderService) {
        this.orderService = orderService;
    }

    // READ ALL SALES / INVOICES
    @GetMapping
    public List<Sale> getAllSales() {
        return orderService.getAllSales();
    }

    // READ ONE SALE
    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return orderService.getSaleById(id);
    }

    // UPDATE SALE PAYMENT STATUS
    @PutMapping("/{id}")
    public Sale updateSaleStatus(@PathVariable Long id, @RequestParam String paymentStatus) {
        return orderService.updateSaleStatus(id, paymentStatus);
    }

    // DELETE / CANCEL SALE
    @DeleteMapping("/{id}")
    public String deleteSale(@PathVariable Long id) {
        orderService.deleteSale(id);
        return "Sale #" + id + " record removed successfully.";
    }
}
