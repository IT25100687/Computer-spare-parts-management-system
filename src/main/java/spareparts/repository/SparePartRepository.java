package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.SparePart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {
    List<SparePart> findByPartNameIgnoreCase(String partName);
}
