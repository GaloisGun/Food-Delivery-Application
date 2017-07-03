package demo;


import demo.Service.MenuItemService;
import demo.Service.RestaurantInfoService;
import demo.controller.MenuInfoRestController;
import demo.controller.RestaurantBulkUploadController;
import demo.domain.MenuItem;
import demo.domain.RestaurantInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantControllerTest {

    private RestaurantInfoService restaurantInfoService;
    private RestaurantBulkUploadController restaurantBulkUploadController;
    private List<RestaurantInfo> restaurantInfoTestList;

    private MenuItemService menuItemService;
    private MenuInfoRestController menuInfoRestController;
    private List<MenuItem> menuItemTestList;

    @Before
    public void setupMock() {
        restaurantInfoService = mock(RestaurantInfoService.class);
        restaurantBulkUploadController = new RestaurantBulkUploadController(restaurantInfoService);
        restaurantInfoTestList = new ArrayList<RestaurantInfo>();

        menuItemService = mock(MenuItemService.class);
        menuInfoRestController = new MenuInfoRestController(menuItemService);
        menuItemTestList = new ArrayList<MenuItem>();

        restaurantInfoTestList.add(new RestaurantInfo("bianyifang", "chaoyangmen","haochi123"));
        restaurantInfoTestList.add(new RestaurantInfo("donglaishun", "caishikou","haochi456"));

        menuItemTestList.add(new MenuItem("duck", 35.0, "real peking duck", "haochi123"));
        menuItemTestList.add(new MenuItem("sopu", 12.0, "duck soup", "haochi123"));
        menuItemTestList.add(new MenuItem("bone", 16.0, "duck bones", "haochi123"));
        menuItemTestList.add(new MenuItem("lamb", 12.0, "fresh lamb", "haochi456"));
        menuItemTestList.add(new MenuItem("bay choy", 5.0, "fresh bay choy", "haochi456"));
        menuItemTestList.add(new MenuItem("cellophane noodles", 5.0, "good Cellophane noodles", "haochi456"));
    }

    @Test
    public void whenServicesTest_returnSavedList() {
        List<RestaurantInfo> restaurantInfos = new ArrayList<RestaurantInfo>();
        restaurantInfos.add(new RestaurantInfo("bianyifang", "chaoyangmen","haochi123"));
        restaurantInfos.add(new RestaurantInfo("donglaishun", "caishikou","haochi456"));

        when(restaurantInfoService.saveRestaurantInfos(restaurantInfos)).thenReturn(restaurantInfoTestList);

        assertThat(restaurantBulkUploadController.upload(restaurantInfoTestList)).size().isEqualTo(2);
        assertThat(restaurantBulkUploadController.upload(restaurantInfoTestList).get(0)).isEqualTo(restaurantInfos.get(0));
        assertThat(restaurantBulkUploadController.upload(restaurantInfoTestList).get(1)).isEqualTo(restaurantInfos.get(1));

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("duck", 35.0, "real peking duck", "haochi123"));
        menuItems.add(new MenuItem("sopu", 12.0, "duck soup", "haochi123"));
        menuItems.add(new MenuItem("bone", 16.0, "duck bones", "haochi123"));
        menuItems.add(new MenuItem("lamb", 12.0, "fresh lamb", "haochi456"));
        menuItems.add(new MenuItem("bay choy", 5.0, "fresh bay choy", "haochi456"));
        menuItems.add(new MenuItem("cellophane noodles", 5.0, "good Cellophane noodles", "haochi456"));

        when(menuItemService.saveMenu(menuItemTestList)).thenReturn(menuItems);
        assertThat(menuInfoRestController.upload(menuItemTestList)).size().isEqualTo(6);
    }
}
