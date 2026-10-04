package spareparts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spareparts.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
