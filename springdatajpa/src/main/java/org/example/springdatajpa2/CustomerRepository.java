package org.example.springdatajpa2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    //각 고객과 고객의 주문 수를 알고 싶다. (주문없는 고객도 보고 싶다면??)
    @Query("SELECT c, count(o) FROM Customer c  LEFT JOIN  c.orders o GROUP BY c")
    List<Object[]> findCustomerOrderCount();

    //고객의 세부 정보와 그 고객이 가장 최근 주문을 조회해주세요.

    //평균나이보다 많은 고객을 조회해 주세요. 
}
