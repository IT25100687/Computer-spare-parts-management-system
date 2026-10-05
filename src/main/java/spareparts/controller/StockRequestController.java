package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.model.StockRequest;
import spareparts.service.StockRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/stock-requests")
public class StockRequestController {

    private final StockRequestService stockRequestService;

    public StockRequestController(StockRequestService stockRequestService) {
        this.stockRequestService = stockRequestService;
    }

    @GetMapping
    public List<StockRequest> getAllStockRequests() {
        return stockRequestService.getAllStockRequests();
    }

    @GetMapping("/{id}")
    public StockRequest getStockRequestById(@PathVariable Long id) {
        return stockRequestService.getStockRequestById(id);
    }

    @PostMapping
    public StockRequest createStockRequest(@RequestBody StockRequest request) {
        return stockRequestService.createStockRequest(request);
    }

    @PutMapping("/{id}")
    public StockRequest updateStockRequestStatus(@PathVariable Long id, @RequestParam String status) {
        return stockRequestService.updateStockRequestStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteStockRequest(@PathVariable Long id) {
        stockRequestService.deleteStockRequest(id);
        return "Stock Request #" + id + " deleted successfully.";
    }
}
