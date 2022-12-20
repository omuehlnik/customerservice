package de.omuehlnikel.tutorials.customerservice.service;

import de.omuehlnikel.tutorials.customerservice.exception.EntityAlreadyExistsException;
import de.omuehlnikel.tutorials.customerservice.exception.EntityNotFoundException;
import de.omuehlnikel.tutorials.customerservice.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static List<Customer> customers;

    private static final String ALEREADY_EXISTS_MESSAGE = "Customer with firstName: %s and lastName: %s is already existing";

    public List<Customer> getCustomers() {
        initCustomers();
        return customers;
    }

    private void initCustomers() {
        if (customers == null) {
            Customer customer1= new Customer();
            customer1.setId(UUID.randomUUID().toString());
            customer1.setFirstName("Albert");
            customer1.setLastName("Einstein");
            customer1.setAge(55);

            Customer customer2 = new Customer();
            customer2.setId(UUID.randomUUID().toString());
            customer2.setFirstName("Otto");
            customer2.setLastName("Hahn");
            customer2.setAge(60);

            customers = new ArrayList<>();
            customers.add(customer1);
            customers.add(customer2);
        }
    }

    public Customer getCustomerById(String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow( () -> new EntityNotFoundException(String.format("Customer not found with id: %s", id)));
    }

    public Customer createCustomer(Customer customer) {

        checkExistingCustomer(customer);
        customer.setId(UUID.randomUUID().toString());
        customers.add(customer);
        return customer;
    }

    private static void checkExistingCustomer(Customer customer) {
        customers.stream()
                .filter(cus -> cus.getFirstName().equals(customer.getFirstName()))
                .filter(cus -> cus.getLastName().equals(customer.getLastName()))
                .findFirst()
                .ifPresent(cus -> {
                    throw new EntityAlreadyExistsException(String.format(ALEREADY_EXISTS_MESSAGE, customer.getFirstName(), customer.getLastName()));
                });
    }

    public void deleteCustomer(String id) {
        customers=customers.stream()
                .filter(customer -> !customer.getId().equals(id))
                .collect(Collectors.toList());
    }
}
