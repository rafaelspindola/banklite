package com.banklite.service;

import com.banklite.model.Customer;
import com.banklite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Customer> update(Long id, Customer updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setEmail(updatedData.getEmail());
            existing.setCpf(updatedData.getCpf());
            existing.setPhone(updatedData.getPhone());
            existing.setAddress(updatedData.getAddress());
            return repository.save(existing);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
