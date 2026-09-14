package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
