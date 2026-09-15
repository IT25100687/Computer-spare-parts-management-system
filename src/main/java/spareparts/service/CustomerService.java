package spareparts.service;

import org.springframework.stereotype.Service;
import spareparts.repository.CustomerRepository;
import spareparts.model.Customer;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getRegistrationDate() == null) {
            customer.setRegistrationDate(LocalDateTime.now());
        }
        if (customer.getCustomerType() == null || customer.getCustomerType().isEmpty()) {
            customer.setCustomerType("Individual");
        }
        if (customer.getStatus() == null || customer.getStatus().isEmpty()) {
            customer.setStatus("Active");
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer details) {
        Customer existing = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (details.getName() != null) existing.setName(details.getName());
        if (details.getEmail() != null) existing.setEmail(details.getEmail());
        if (details.getPhone() != null) existing.setPhone(details.getPhone());
        if (details.getAddress() != null) existing.setAddress(details.getAddress());
        if (details.getCustomerType() != null) existing.setCustomerType(details.getCustomerType());
        if (details.getStatus() != null) existing.setStatus(details.getStatus());
        return customerRepository.save(existing);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
