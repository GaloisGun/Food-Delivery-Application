package demo.Service;

import demo.domain.RestaurantInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RestaurantInfoService {

    void deleteAll();

    List<RestaurantInfo> saveRestaurantInfos(List<RestaurantInfo> restaurantInfos);

    Page<RestaurantInfo> findByRestaurantName(String name, Pageable pageable);
    RestaurantInfo findFirstByRestaurantName(String name);
    RestaurantInfo findByRestaurantId(String restaurantId);

}
