package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity(name = "foodItem")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class FoodItemInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String foodId;
    private String foodName;
    private String description;
    private String restaurantName;
    private double price;
    //private double tax;
    private int quantity;
    private String restaurantId;

    @ManyToOne
    private OrderInfo orderInfo;


    public FoodItemInfo(String foodId, String foodName, double price, String description, int quantity, String res_id, OrderInfo orderInfo) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.restaurantId = res_id;
        this.orderInfo = orderInfo;
    }
}
