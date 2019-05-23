package com.example.javatraining.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.javatraining.domain.Customer;
import com.example.javatraining.domain.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pkaul on 5/22/19
 */

public class CustomerServiceTest {

    CustomerService customerService;


    @Before
    public void  setup() {
        customerService = new CustomerService();
    }

    @Test
    public void getCustomerById() {
        createCustomerMap();
        assertEquals(1,customerService.getCustomerById(1).getId());
        assertEquals("John",customerService.getCustomerById(1).getName());
    }

    @Test
    public void getAllCustomers() {
        createCustomerMap();
        assertEquals(1,customerService.getAllCustomers().size());
    }

    @Test
    public void insertCustomer() {
        Customer customerDao = customerService.insertCustomer(2, "Doe", "Privileged");
        assertEquals(2,customerDao.getId());
    }

    @Test
    public void getSortedCustomers() {
        createCustomerMap();
        customerService.insertCustomer(2, "Doe", "Privileged");
        List<Customer> sortedList = customerService.getSortedCustomers();
        assertEquals(2,sortedList.size());
        assertEquals("Doe",sortedList.get(0).getName());
    }

    @Test
    public void getSortedCustomersById() {
        createCustomerMap();
        customerService.insertCustomer(2, "Doe", "Privileged");
        List<Customer> sortedList = customerService.getSortedCustomersById();
        System.out.print(sortedList);
        assertEquals(2,sortedList.size());
        assertEquals("Doe",sortedList.get(1).getName());
    }

    @Test
    public void filterBy() {
        createCustomerMap();
        customerService.insertCustomer(2, "Doe", "Privileged");
        customerService.insertCustomer(3, "Jane", "RESTRICTED");
        List<Customer> customerList = customerService.filterBy(customerService.getAllCustomers(), customer -> customer.getStatus().equals(Status.RESTRICTED));
        System.out.print(customerList);
        assertEquals(1,customerList.size());
        assertEquals("Jane",customerList.get(0).getName());
    }

    private void createCustomerMap() {
        Customer customerDao = new Customer(1,"John", Status.NORMAL);
        customerService.customers.put(1,customerDao);
    }



}