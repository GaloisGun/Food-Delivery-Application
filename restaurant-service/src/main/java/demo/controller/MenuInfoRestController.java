package demo.controller;

import demo.Service.MenuItemService;
import demo.Service.RestaurantInfoService;
import demo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuInfoRestController {
    //private RestaurantInfoService restaurantInfoService;
    private MenuItemService menuItemService;

    @Autowired
    public MenuInfoRestController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
        //this.restaurantInfoService = restaurantInfoService;
    }

    @RequestMapping(value = "/restaurant/menu", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<MenuItem> upload(@RequestBody List<MenuItem> menuItemList) {
        return this.menuItemService.saveMenu(menuItemList);
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu")
    public Page<MenuItem> showMenuByRestaurantId(@PathVariable String restaurantId, Pageable pageable) {
        return this.menuItemService.showMenu(restaurantId, pageable);
    }

    @RequestMapping(value = "/restaurant/menu", method = RequestMethod.DELETE)
    public void purge() {
        this.menuItemService.deleteAll();
    }
}
