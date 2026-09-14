package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.SparePart;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {
}
