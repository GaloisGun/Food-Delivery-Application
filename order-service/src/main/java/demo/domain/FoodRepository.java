package demo.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodItemInfo, Long> {
    List<FoodItemInfo> findByOrderInfo(OrderInfo orderInfo);
    Page<FoodItemInfo> findByRestaurantId (String restaurantId, Pageable pageable);
}
