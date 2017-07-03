package demo;

import demo.domain.MenuItem;
import demo.domain.MenuItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
public class ItemRepositoryTest {

    @Autowired
    private MenuItemRepository itemRepository;

    @Test
    public void whenSaveItem_returnSavedItem() {
        final String name = "pekingDuck";
        final double price = 35.0;
        final String description = "The real peking duck";
        final String restaurantId = "Bianyifang001";

        MenuItem test = this.itemRepository.save(new MenuItem(name, price, description, restaurantId));

        assertThat(this.itemRepository.findFirstByItemName("pekingDuck").getItemName()).isEqualTo(name);
        assertThat(this.itemRepository.findFirstByItemName("pekingDuck").getItemPrice()).isEqualTo(price);
        assertThat(this.itemRepository.findFirstByItemName("pekingDuck").getItemDescription()).isEqualTo(description);
        assertThat(this.itemRepository.findFirstByItemName("pekingDuck").getRestaurantId()).isEqualTo(restaurantId);

        itemRepository.deleteAll();
    }

    public void whenFindFirstByName_returnMenuInfo() {
        final String name1 = "pekingDuck";
        final double price1 = 35.0;
        final String description1 = "The real peking duck";
        final String restaurantId1 = "Bianyifang001";

        final String name2 = "baodu";
        final double price2 = 12.5;
        final String description2 = "Guoyin";
        final String restaurantId2 = "Bianyifang001";

        MenuItem kaoya = new MenuItem(name1, price1, description1, restaurantId1);
        MenuItem baodu = new MenuItem(name2, price2, description2, restaurantId2);

        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
        menuItemList.add(kaoya);
        menuItemList.add(baodu);

        this.itemRepository.deleteAll();
        List<MenuItem> test = this.itemRepository.save(menuItemList);
        assertThat(this.itemRepository.findByRestaurantInfoId("Bianyifang001", new PageRequest(0,2))).size().isEqualTo(2);
        assertThat(this.itemRepository.findByRestaurantInfoId("Bianyifang001", new PageRequest(0,2))).isEqualTo(restaurantId1);
        assertThat(this.itemRepository.findByRestaurantInfoId("Bianyifang001", new PageRequest(0,2))).isEqualTo(restaurantId1);

        itemRepository.deleteAll();



    }
}
