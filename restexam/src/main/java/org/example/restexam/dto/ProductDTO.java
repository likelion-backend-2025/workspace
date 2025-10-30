package org.example.restexam.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.restexam.domain.Product;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    @NotBlank(message = "상품명은 반드시 입력 해야합니다. ")
    private String name;
    @Min(value = 1, message = "가격은 1 이상 이어야 합니다.")
    private int price;


    //Entity -> Dto로 변환하는 메서드
    public static ProductDTO fromEntity(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}
