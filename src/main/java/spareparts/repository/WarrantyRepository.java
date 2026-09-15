package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Warranty;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
