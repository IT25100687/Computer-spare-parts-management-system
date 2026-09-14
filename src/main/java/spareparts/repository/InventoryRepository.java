package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Inventory;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
