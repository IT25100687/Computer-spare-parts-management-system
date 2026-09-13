package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}