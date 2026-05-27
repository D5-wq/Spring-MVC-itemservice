package hello.itemservice.domain.item;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class Item {

    private Long id;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String itemName;

    @NotNull(message = "가격을 입력해주세요!")
    @Range(min = 1000, max = 1000000, message = "가격은 1,000 ~ 1,000,000 까지 허용합니다.")
    private Integer price;

    @NotNull(message = "수량을 입력해주세요!")
    @Max(value = 9999, message = "수량은 최대 9,999 까지 허용합니다.")
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
