package demo.Service;

import demo.domain.RestaurantInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wuyufei on 6/24/17.
 */
public interface RestaurantInfoService {

    void deleteAll();

    RestaurantInfo findByRestaurantName(String restaurantName);




}
