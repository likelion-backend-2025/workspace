package org.example.springdatajpa2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    //고객정보를 얻어오고 싶다.  getCustomer(Long id)
    public Customer getCustomer(Long id) {
        return  customerRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id+"에 해당하는 고객을 찾을 수 없습니다."));
    }
    //전체 고객정보를 얻어오고 싶다면?
    public List<Customer> getCustomers() {
        
    }

    //고객을 추가하고 싶다면?  createCustomer(String name, String email)

    @Transactional
    public Customer createCustomer(String name, String email){
        Customer customer = new Customer(name,email);


        return customerRepository.save(customer);
    }

    //고객정보를 수정하고 싶다면??


    //고객을 삭제하고 싶다면? 
}
