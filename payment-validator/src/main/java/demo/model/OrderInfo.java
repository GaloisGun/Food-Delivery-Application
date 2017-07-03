package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderInfo {

    private String orderId;
    private String restaurantId;
    private String userId;
    private String notes;
    private String restaurantName;
    private String deliveryAddress;
    private OrderStatus orderStatus;


    public OrderInfo(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderInfo() {}
}
