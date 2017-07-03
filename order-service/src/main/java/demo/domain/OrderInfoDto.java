package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    private String restaurantName;
    private String userId;
    private int estimatedDeliveryTime;
    private String deliveryAddress;
    private String dietNote;
    private String orderId;
    private double price;

    private List<FoodItemInfo> foodItemInfoList = new ArrayList<FoodItemInfo>();

    private OrderInfo.OrderStatus orderStatus;

    public OrderInfoDto(OrderInfo orderInfo) {
        this.userId = orderInfo.getOrderId();
        this.orderId = orderInfo.getOrderId();
        this.restaurantName = orderInfo.getRestaurantName();
        this.deliveryAddress = orderInfo.getDeliveryAddress();
        this.dietNote = orderInfo.getDietNote();
        this.orderStatus = orderInfo.getOrderStatus();
        this.price = 0.0;
    }

}
