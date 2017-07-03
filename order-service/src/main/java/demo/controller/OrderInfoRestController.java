package demo.controller;

import demo.domain.OrderInfo;
import demo.domain.OrderInfoDto;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderInfoRestController {

    private OrderService orderService;

    @Autowired
    public OrderInfoRestController (OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/restaurant/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderInfo> saveOrdersByRestaurantId(@PathVariable String restaurantId, @RequestBody List<OrderInfo> orderInfoList) {
        return orderService.saveOrdersByRestaurantId(restaurantId, orderInfoList);
    }

    @RequestMapping(value = "restaurant/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderInfo> saveOrders(@RequestBody List<OrderInfo> orderInfoList) {
        return orderService.saveOrderInfos(orderInfoList);
    }

    @RequestMapping(value = "restaurant/{restaurantId}/order")
    public Page<OrderInfo> viewOrdersByRestaurant(@PathVariable String restaurantId, Pageable pageable) {
        return orderService.showOrdersByRestaurant(restaurantId, pageable);
    }

    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.GET)
    public OrderInfoDto viewOrderConfirmation(@PathVariable String orderId) {
        OrderInfo orderInfo = orderService.findFirstByOrderId(orderId);
        if (orderInfo.getOrderStatus().equals(OrderInfo.OrderStatus.Paid)){
            OrderInfoDto orderInfoDto = orderService.showOrderConfirmation(orderId);
            return orderInfoDto;
        }
        return null;
    }

    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.PUT)
    public void updateOrderStatus(@PathVariable String orderId, @ModelAttribute OrderInfo.OrderStatus orderStatus){
        orderService.updateOrderStatus(orderId, orderStatus);
    }


    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }
}
