package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "restaurantInfo")
public interface RestaurantInfoRepository extends PagingAndSortingRepository<RestaurantInfo, Long> {

    void deleteRestaurantInfoByRestaurantName(@Param("restaurantName") String restaurantName);

    @RestResource(rel = "by-name", description = @Description("Find by Name, comma separated"))
    RestaurantInfo findFirstByRestaurantName(@Param("restaurantName") String restaurantName);

    Page<RestaurantInfo> findByRestaurantName(@Param("restaurantName") String restaurantName, Pageable pageable);

    RestaurantInfo findFirstByRestaurantId(@Param("restaurantId") String restaurantId);

    @RestResource
    Page<RestaurantInfo> findAll(Pageable pageable);

    void deleteAll();
}
