package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}