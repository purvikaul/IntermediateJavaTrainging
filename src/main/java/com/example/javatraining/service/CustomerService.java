package com.example.javatraining.service;

import com.example.javatraining.dao.CustomerDao;
import com.example.javatraining.dao.GenericDao;
import com.example.javatraining.domain.Customer;
import com.example.javatraining.domain.Status;
import jdk.net.SocketFlow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by pkaul on 5/22/19
 */
@Component
public class CustomerService {

    GenericDao customers = new CustomerDao();;


    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    public List<Customer> getAllCustomers(){
        return customers.values();
    }

    public Customer insertCustomer(int id, String name, String status){
        Status customerStatus = Status.valueOf(status.toUpperCase());
        if(customerStatus != null){
            Customer customerDao = new Customer(id,name,customerStatus);
            customers.create(customerDao);
            return customerDao;
        }
        return null;
    }

    public List<Customer> getSortedCustomers() {
        List<Customer> customerList = getAllCustomers();
        Collections.sort(customerList);
        return customerList;
    }

    public List<Customer> getSortedCustomersById() {
        List<Customer> customerList = getAllCustomers();
        Collections.sort(customerList,((o1, o2) -> o1.getId() - o2.getId()));
        return customerList;
    }

    public <T> List<T> filterBy(List<T> list,Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        list.forEach(element -> {
            if(condition.test(element)){
                result.add(element);
            }
        });

        return result;
    }

    public List<String> getNames(Status status){
        return getAllCustomers().stream()
                .filter(customer -> customer.getStatus().equals(status))
                .map(Customer::getName)
                .collect(Collectors.toList());
    }
}
