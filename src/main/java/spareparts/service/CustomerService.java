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

    private void validateCustomerFields(String name, String email, String phone) {
        if (name != null) {
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Customer name cannot be empty.");
            }
            if (name.matches("^\\d+$") || !name.matches(".*[a-zA-Z].*")) {
                throw new IllegalArgumentException("Customer name must contain valid words/letters and cannot be purely numeric.");
            }
        }
        if (phone != null && !phone.trim().isEmpty()) {
            String digits = phone.replaceAll("\\D", "");
            if (digits.length() != 10) {
                throw new IllegalArgumentException("Phone number must contain exactly 10 digits (e.g. 0771234567).");
            }
        }
        if (email != null && !email.trim().isEmpty() && !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer phone number is required.");
        }
        validateCustomerFields(customer.getName(), customer.getEmail(), customer.getPhone());

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
        validateCustomerFields(details.getName(), details.getEmail(), details.getPhone());

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
