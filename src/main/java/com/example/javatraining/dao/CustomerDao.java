package com.example.javatraining.dao;

import com.example.javatraining.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pkaul on 5/22/19
 */

public class CustomerDao implements GenericDao {

    Map<Integer, Customer> customers = new HashMap<>();


    @Override
    public Customer get(int id) {
        return customers.get(id);
    }

    @Override
    public void put(int id, Customer customer){
        customers.put(id,customer);
    }

    @Override
    public void create(Customer customer){
        customers.put(customer.getId(),customer);
    }

    @Override
    public List<Customer> values(){
        return  new ArrayList<>(customers.values());
    }

}
