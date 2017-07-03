package demo.service.impl;

import demo.domain.*;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private FoodRepository foodRepository;

    @Autowired
    public OrderServiceImpl (OrderRepository orderRepository, FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderInfo> saveOrdersByRestaurantId(String restaurantId, List<OrderInfo> orderInfos){
        for(OrderInfo orderInfo: orderInfos){
            orderInfo.setRestaurantId(restaurantId);
        }
        return orderRepository.save(orderInfos);
    }

    @Override
    public List<OrderInfo> saveOrderInfos(List<OrderInfo> orderInfoList) {
        return orderRepository.save(orderInfoList);
    }

    @Override
    public Page<OrderInfo> showOrdersByRestaurant(String restaurantId, Pageable pageable) {
        return orderRepository.findByRestaurantId(restaurantId, pageable);
    }

    @Override
    public Page<OrderInfo> showOrdersByUser(String userId, Pageable pageable) {
        return orderRepository.findByUserName(userId, pageable);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public OrderInfoDto showOrderConfirmation(String orderId) {
        final int maxEstimatedTime = 60;
        final int minEstimatedTime = 5;
        Random random = new Random();
        OrderInfo orderInfo = orderRepository.findFirstByOrderId(orderId);
        OrderInfoDto infoDto = new OrderInfoDto(orderInfo);

        if (infoDto.getOrderStatus().equals(OrderInfo.OrderStatus.Paid)) {
            int randomEstimatedTime = random.nextInt(maxEstimatedTime) % (maxEstimatedTime - minEstimatedTime + 1) + minEstimatedTime;
            infoDto.setEstimatedDeliveryTime(randomEstimatedTime);
        }

        List<FoodItemInfo> foodItemInfoList = foodRepository.findByOrderInfo(orderInfo);
        infoDto.setFoodItemInfoList(foodItemInfoList);

        double totalPrice = 0.0;
        for (FoodItemInfo foodItemInfo : foodItemInfoList) {
            totalPrice += foodItemInfo.getPrice() * foodItemInfo.getQuantity();
        }
        infoDto.setPrice(totalPrice);

        return infoDto;
    }

    @Override
    public OrderInfo findFirstByOrderId(String orderId) {
        return orderRepository.findFirstByOrderId(orderId);
    }

    @Override
    public void updateOrderStatus(String orderId, OrderInfo.OrderStatus orderStatus) {

        OrderInfo orderInfo = findFirstByOrderId(orderId);
        orderInfo.setOrderStatus(orderStatus);
        orderRepository.save(orderInfo);
    }


}
