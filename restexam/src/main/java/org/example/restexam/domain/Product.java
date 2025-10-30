package org.example.restexam.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.restexam.dto.ProductDTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String name;
    private int price;

    //DTO-->Entity로 변환하는 메서드
    public static Product fromDto(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

}
//Product 클래스를 기준으로,  아래 제시된 부분을 만들어서..  완성해보세요.
//엔티티, repository, service, controller,  .http  까지 작성하실수 있으실 거예요^^

//UserController - > 응답을 좀 더 세심하게 생성해주세요.


