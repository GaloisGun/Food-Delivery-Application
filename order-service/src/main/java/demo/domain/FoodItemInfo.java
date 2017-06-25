package demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FoodItemInfo {
    @Id
    private Long id;

    private String foodName;
    private double price;
    private int quantity;
}
