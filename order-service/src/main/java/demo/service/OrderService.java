package demo.service;


import demo.domain.OrderInfo;
import demo.domain.OrderInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<OrderInfo> saveOrdersByRestaurantId(String resterauntId, List<OrderInfo> orderInfos);

    List<OrderInfo> saveOrderInfos(List<OrderInfo> orderInfoList);

    Page<OrderInfo> showOrdersByRestaurant(String restaurantId, Pageable pageable);

    Page<OrderInfo> showOrdersByUser(String userId, Pageable pageable);

    void deleteOrder(String orderId);

    OrderInfoDto showOrderConfirmation(String orderId);

    void updateOrderStatus(String orderId, OrderInfo.OrderStatus orderStatus);

    OrderInfo findFirstByOrderId(String orderId);

}
