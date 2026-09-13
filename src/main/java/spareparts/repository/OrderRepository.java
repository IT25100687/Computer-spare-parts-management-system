package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}