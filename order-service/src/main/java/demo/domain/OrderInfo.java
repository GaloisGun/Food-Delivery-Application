package demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.util.List;

/**
 * Created by wuyufei on 6/24/17.
 */
@Data
public class OrderInfo {
    @Id
    private Long id;

    private List<FoodItemInfo> foodItemInfoList;
    private double price;

}
