package demo.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Page<MenuItem> findByRestaurantInfoId(String restaurantId, Pageable pageable);

    MenuItem findFirstByItemName(String itemName);
}
