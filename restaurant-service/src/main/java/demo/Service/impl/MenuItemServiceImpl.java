package demo.Service.impl;

import demo.Service.MenuItemService;
import demo.domain.MenuItem;
import demo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }


    @Override
    public Page<MenuItem> showMenu(String restaurantId, Pageable pageable) {
        return menuItemRepository.findByRestaurantInfoId(restaurantId, pageable);
    }

    @Override
    public List<MenuItem> saveMenu(List<MenuItem> menuItemList) {
        return menuItemRepository.save(menuItemList);
    }

    @Override
    public void deleteAll() {
        menuItemRepository.deleteAll();
    }
}
