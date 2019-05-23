package com.example.javatraining.dao;

import com.example.javatraining.domain.Customer;

import java.util.List;

/**
 * Created by pkaul on 5/22/19
 */
public interface GenericDao {
    Customer get(int id);

    void put(int id, Customer customer);

    void create(Customer customer);

    List<Customer> values();
}
