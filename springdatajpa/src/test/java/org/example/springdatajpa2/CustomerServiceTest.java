package org.example.springdatajpa2;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Test
    void getCustomers(){
        List<Customer> customers = customerService.getCustomers();

        customers.forEach(System.out::println);
    }

    @Test
    void getCustomerByEmail(){
        Customer customerByEmail = customerService.getCustomerByEmail("hong@example.com");

        assertThat(customerByEmail).isNotNull();
        assertThat(customerByEmail.getId()).isNotNull();
        assertThat(customerByEmail.getName()).isEqualTo("홍길동");
    }

    @Test
    void getCustomerByEmailNotFound(){
//        Customer customerByEmail = customerService.getCustomerByEmail("nofound@example.com");
        assertThatThrownBy(()->customerService.getCustomerByEmail("notfound@exam.com"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateCustomer(){
        Customer customer = new Customer("홍동길","hong@gmail.com");
        customer.setAge(35);
        customer.setId(1L);

        Customer updateCustomer = customerService.updateCustomer(customer);

        assertThat(updateCustomer.getName()).isEqualTo("홍동길");



    }

    @Test
    void deleteCustomer(){
        List<Customer> customers = customerService.getCustomers();
        customerService.deleteCustomer(1L);

        assertThatThrownBy(()-> customerService.getCustomer(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("");

    }
}