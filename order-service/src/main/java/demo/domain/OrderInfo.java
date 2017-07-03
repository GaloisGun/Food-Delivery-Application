package demo.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "orderInfo")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class OrderInfo {

    public enum OrderStatus {
        Created, Processing, Paid, Finished, Cancelld
    }


    @Id
    @GeneratedValue
    private Long id;

    private String orderId;
    private String userName;
    private String deliveryAddress;
    private String restaurantId;
    private String restaurantName;
    private String dietNote;
    private Date timestamp = new Date();
    private OrderStatus orderStatus = OrderStatus.Created;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orderInfo")
    private List<FoodItemInfo> foodItemInfoList;

    public OrderInfo() {}

    @JsonCreator
    public OrderInfo(@JsonProperty("Id") Long Id) {
        this.id = Id;
    }

    public OrderInfo(String orderId, String userName, String restaurantName) {
        this.orderId = orderId;
        this.userName = userName;
        this.restaurantName = restaurantName;
        this.orderStatus = OrderStatus.Processing;
    }

    public OrderInfo(String orderId, String userName, String restaurantId, String deliveryAddress, String restaurantName, String dietNote) {
        this.orderId = orderId;
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.restaurantName = restaurantName;
        this.dietNote = dietNote;
        this.orderStatus = OrderStatus.Processing;
    }
}
