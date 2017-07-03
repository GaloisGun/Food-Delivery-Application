package demo;

import demo.domain.RestaurantInfo;
import demo.domain.RestaurantInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantInfoRepository repository;

    @Test
    public void whenSaveRestaurant_returnSavedRestaurant() {
        final String name = "bianyifang" ;
        final String address = "chaoyangmen";

        RestaurantInfo result = this.repository.save(new RestaurantInfo(name, address));
        assertThat(this.repository.findFirstByRestaurantName("bianyifang").getRestaurantName()).isEqualTo(name);
        assertThat(this.repository.findFirstByRestaurantName("bianyifang").getRestaurantAddress()).isEqualTo(address);

        repository.deleteAll();
    }
}
