package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spareparts.model.StockRequest;

@Repository
public interface StockRequestRepository extends JpaRepository<StockRequest, Long> {
}
