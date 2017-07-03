package demoTest;

import demo.OrderServiceApplication;
import demo.domain.*;
import demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class OrderServiceMvcTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodRepository foodRepository;

    private final String parseType = "application/json;charset=UTF-8";
    private MockMvc mockMvc;

    private List<OrderInfo> testOrderList = new ArrayList<OrderInfo>();
    private List<FoodItemInfo> testFoodList = new ArrayList<FoodItemInfo>();
    String testOrderId = "testOrderId1";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        OrderInfo orderInfo1 = new OrderInfo("orderId1","userName1","restaurantId1","delivery_address1","res_name1","note1");
        OrderInfo orderInfo2 = new OrderInfo("orderId2","userName2","restaurantId2","delivery_address2","res_name2","note2");
        testOrderList.add(orderInfo1);
        testOrderList.add(orderInfo2);

        FoodItemInfo item1 = new FoodItemInfo("foodId1","foodName1",1.0,"description1",1,"restaurantId1", orderInfo1);
        FoodItemInfo item2 = new FoodItemInfo("foodId2","foodName2",2.0,"description2",2,"restaurantId2", orderInfo1);
        testFoodList.add(item1);
        testFoodList.add(item2);
    }

    @Test
    public void saveOrdersTest() throws Exception {

        List<OrderInfo> result = this.orderService.saveOrderInfos(testOrderList);
        assertThat(result.size()).isEqualTo(2);
        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }

    @Test
    public void findByOrderIdTest() throws Exception {
        this.orderService.saveOrderInfos(testOrderList);
        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderId()).isEqualTo("orderId1");
        assertThat(this.orderService.findFirstByOrderId("orderId3")).isEqualTo(null);
        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }


    @Test
    public void changeOrderStatusTest() throws Exception {
        this.orderService.saveOrderInfos(testOrderList);
        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderStatus()).isEqualTo(OrderInfo.OrderStatus.Processing);


        this.orderService.updateOrderStatus("orderId1", OrderInfo.OrderStatus.Paid);

        assertThat(this.orderRepository.findAll().size()).isEqualTo(2);

        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderStatus()).isEqualTo(OrderInfo.OrderStatus.Paid);
        assertThat(this.orderService.findFirstByOrderId("orderId2").getOrderStatus()).isEqualTo(OrderInfo.OrderStatus.Processing);

        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }

    @Test
    public void viewOrderConfirmationTest() throws Exception {

        List<OrderInfo> saveOrderResult = this.orderRepository.save(testOrderList);
        List<FoodItemInfo> saveItemResult = this.foodRepository.save(testFoodList);

        OrderInfoDto orderInfoDto = this.orderService.showOrderConfirmation(testOrderId);
        assertThat(orderInfoDto.getOrderId()).isEqualTo(testOrderId);
        assertThat(orderInfoDto.getUserId()).isEqualTo("userId1");
        assertThat(orderInfoDto.getDeliveryAddress()).isEqualTo("delivery_address1");
        assertThat(orderInfoDto.getDietNote()).isEqualTo("note1");
        assertThat(orderInfoDto.getRestaurantName()).isEqualTo("res_name1");
        assertThat(orderInfoDto.getOrderStatus()).isEqualTo(OrderInfo.OrderStatus.Processing);
        assertThat(orderInfoDto.getEstimatedDeliveryTime()).isEqualTo(0);

        assertThat(orderInfoDto.getFoodItemInfoList().size()).isEqualTo(2);
        assertThat(orderInfoDto.getFoodItemInfoList().get(0).getPrice()).isEqualTo(1.0);
        assertThat(orderInfoDto.getFoodItemInfoList().get(1).getPrice()).isEqualTo(2.0);
        assertThat(orderInfoDto.getFoodItemInfoList().get(0).getQuantity()).isEqualTo(1);
        assertThat(orderInfoDto.getFoodItemInfoList().get(1).getQuantity()).isEqualTo(2);
        assertThat(orderInfoDto.getPrice()).isEqualTo(5.0);

        this.orderService.updateOrderStatus(testOrderId, OrderInfo.OrderStatus.Paid);

        OrderInfoDto orderInfoDto1 = this.orderService.showOrderConfirmation(testOrderId);
        assertThat(orderInfoDto1.getOrderId()).isEqualTo(testOrderId);
        assertThat(orderInfoDto1.getUserId()).isEqualTo("userId1");
        assertThat(orderInfoDto1.getDeliveryAddress()).isEqualTo("delivery_address1");
        assertThat(orderInfoDto1.getDietNote()).isEqualTo("note1");
        assertThat(orderInfoDto1.getRestaurantName()).isEqualTo("res_name1");
        assertThat(orderInfoDto1.getOrderStatus()).isEqualTo(OrderInfo.OrderStatus.Paid);
        assertThat(orderInfoDto1.getEstimatedDeliveryTime()).isBetween(5,60);

        this.mockMvc.perform(get("/restaurant/order/").accept(MediaType.parseMediaType(parseType)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.parseMediaType(parseType)));


        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }

    @Test
    public void rootTest() throws Exception {

        this.mockMvc.perform(get("/restaurant/order/").accept(MediaType.parseMediaType(parseType)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(parseType)));

    }

}
