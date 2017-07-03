package demo.Service;


import demo.domain.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuItemService {
    Page<MenuItem> showMenu(String restaurantId, Pageable pageable);
    List<MenuItem> saveMenu(List<MenuItem> menuItemList);
    void deleteAll();
}
