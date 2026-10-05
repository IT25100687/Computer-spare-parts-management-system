package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.model.StockRequest;
import spareparts.repository.StockRequestRepository;
import spareparts.repository.SparePartRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockRequestService {

    private final StockRequestRepository stockRequestRepository;
    private final SparePartRepository sparePartRepository;

    public StockRequestService(StockRequestRepository stockRequestRepository, SparePartRepository sparePartRepository) {
        this.stockRequestRepository = stockRequestRepository;
        this.sparePartRepository = sparePartRepository;
    }

    public List<StockRequest> getAllStockRequests() {
        return stockRequestRepository.findAll();
    }

    public StockRequest getStockRequestById(Long id) {
        return stockRequestRepository.findById(id).orElse(null);
    }

    public StockRequest createStockRequest(StockRequest request) {
        if (request.getSparePartId() == null || request.getSparePartId() <= 0) {
            throw new IllegalArgumentException("Spare Part ID is required.");
        }
        if (!sparePartRepository.existsById(request.getSparePartId())) {
            throw new IllegalArgumentException("Invalid Spare Part ID #" + request.getSparePartId() + "! This spare part does not exist in the catalog.");
        }
        if (request.getRequestedQuantity() == null || request.getRequestedQuantity() <= 0) {
            throw new IllegalArgumentException("Requested quantity must be a positive number (> 0).");
        }
        if (request.getRequestDate() == null) {
            request.setRequestDate(LocalDateTime.now());
        }
        if (request.getRequestStatus() == null || request.getRequestStatus().trim().isEmpty()) {
            request.setRequestStatus("Pending Reorder");
        }
        return stockRequestRepository.save(request);
    }

    public StockRequest updateStockRequestStatus(Long id, String newStatus) {
        StockRequest existing = stockRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock request not found with ID: " + id));
        if (newStatus != null && !newStatus.trim().isEmpty()) {
            existing.setRequestStatus(newStatus);
        }
        return stockRequestRepository.save(existing);
    }

    public void deleteStockRequest(Long id) {
        stockRequestRepository.deleteById(id);
    }
}
