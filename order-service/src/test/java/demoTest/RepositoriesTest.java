package demoTest;

import demo.domain.FoodItemInfo;
import demo.domain.FoodRepository;
import demo.domain.OrderInfo;
import demo.domain.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration
public class RepositoriesTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodRepository foodRepository;

    List<OrderInfo> orderInfosTest;
    List<FoodItemInfo> foodItemInfosTest;

    @Before
    public void setup() {
        orderInfosTest = new ArrayList<OrderInfo>();
        foodItemInfosTest = new ArrayList<FoodItemInfo>();

        OrderInfo orderInfo1 = new OrderInfo("orderId1", "restaurantId1", "userId1", "note1","res_name1","delivery_address1");
        OrderInfo orderInfo2 = new OrderInfo("orderId2", "restaurantId2", "userId1", "note2","res_name2","delivery_address1");
        orderInfosTest.add(orderInfo1);
        orderInfosTest.add(orderInfo2);

        foodItemInfosTest.add(new FoodItemInfo("foodId1","foodName1",1.0,"description1",1,"res_Id1",orderInfo1));
        foodItemInfosTest.add(new FoodItemInfo("foodId2","foodName2",2.0,"description2",2,"res_Id1",null));
        foodItemInfosTest.add(new FoodItemInfo("foodId3","foodName3",3.0,"description3",3,"res_Id1",orderInfo1));
        foodItemInfosTest.add(new FoodItemInfo("foodId4","foodName4",4.0,"description4",4,"res_Id1",orderInfo2));
    }

    @Test
    public void whenSaveOrders_returnSavedList() {
        List<OrderInfo> result = this.orderRepository.save(orderInfosTest);
        List<FoodItemInfo> resultItems = this.foodRepository.save(foodItemInfosTest);

        assertThat(this.orderRepository.count()).isEqualTo(2);
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getOrderId()).isEqualTo("orderId2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getRestaurantId()).isEqualTo("restaurantId2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getUserName()).isEqualTo("userId1");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getDietNote()).isEqualTo("note2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getRestaurantName()).isEqualTo("res_name2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getDeliveryAddress()).isEqualTo("delivery_address1");

        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }

    @Test
    public void whenSaveItem_returnSavedItemList() {

        List<OrderInfo> resultOrders = this.orderRepository.save(orderInfosTest);
        List<FoodItemInfo> resultItems = this.foodRepository.save(foodItemInfosTest);

        assertThat(this.foodRepository.count()).isEqualTo(4);
        assertThat(this.foodRepository.findAll().get(0).getFoodId()).isEqualTo("foodId1");
        assertThat(this.foodRepository.findAll().get(0).getFoodName()).isEqualTo("foodName1");
        assertThat(this.foodRepository.findAll().get(0).getPrice()).isEqualTo(1.0);
        assertThat(this.foodRepository.findAll().get(0).getDescription()).isEqualTo("description1");
        assertThat(this.foodRepository.findAll().get(0).getQuantity()).isEqualTo(1);
        assertThat(this.foodRepository.findAll().get(0).getRestaurantId()).isEqualTo("res_Id1");
        assertThat(this.foodRepository.findAll().get(0).getOrderInfo().getOrderId()).isEqualTo(orderInfosTest.get(0).getOrderId());
        assertThat(this.orderRepository.findAll().get(0).getFoodItemInfoList().size()).isEqualTo(2);
        assertThat(this.orderRepository.findAll().get(1).getFoodItemInfoList().size()).isEqualTo(1);

        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();
    }

    @Test
    public void whenFindByOrderId_returnOrderInfo() {
        List<OrderInfo> resultOrders = this.orderRepository.save(orderInfosTest);
        List<FoodItemInfo> resultItems = this.foodRepository.save(foodItemInfosTest);
        OrderInfo orderInfo = this.orderRepository.findFirstByOrderId("orderId1");

        assertThat(orderInfo.getOrderId()).isEqualTo("orderId1");
        assertThat(orderInfo.getFoodItemInfoList().size()).isEqualTo(2);
        assertThat(orderInfo.getFoodItemInfoList().get(0)).isEqualTo(orderInfo);
        assertThat(orderInfo.getFoodItemInfoList().get(1)).isEqualTo(orderInfo);

        this.orderRepository.deleteAll();
        this.foodRepository.deleteAll();

    }
}
