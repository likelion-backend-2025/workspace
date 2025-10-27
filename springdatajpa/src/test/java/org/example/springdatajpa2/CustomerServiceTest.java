package org.example.springdatajpa2;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void getCustomer() {
        customerService.getCustomer(1L);
    }

    @Test
    void testGetCustomer() {
    }

    @Test
    void createCustomer() {
        Customer customer = customerService.createCustomer("강경미", "carami@gmail.com");

//        assertNotNull(customer);
//        assertNotNull(customer.getId());

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getEmail()).isEqualTo("carami@gmail.com");

    }
}