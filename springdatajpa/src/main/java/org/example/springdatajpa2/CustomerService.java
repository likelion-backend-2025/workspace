package org.example.springdatajpa2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return  customerRepository.findAll();
    }

    //이메일에 해당하는 고객 정보를 얻어오고 싶다.
    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException(email+"을 사용하는 고객이 없습니다."));
    }


    //고객을 추가하고 싶다면?  createCustomer(String name, String email)

    @Transactional
    public Customer createCustomer(String name, String email){
        Customer customer = new Customer(name,email);


        return customerRepository.save(customer);
    }

    //고객정보를 수정하고 싶다면??
    @Transactional
    public Customer updateCustomer(Customer customer){
        //수정  jpa는 수정해주세요.라는 메서드가 존재하지 않아요.
        Customer updateCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 customer가존재하지 않아요."));
        updateCustomer.setName(customer.getName());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setAge(customer.getAge());

        return updateCustomer;
    }

    //고객을 삭제하고 싶다면?
    @Transactional
    public void deleteCustomer(Long id){
        Customer customer = getCustomer(id);

        customerRepository.delete(customer);
    }
}
