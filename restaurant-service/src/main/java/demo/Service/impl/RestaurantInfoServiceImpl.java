package demo.Service.impl;

import demo.Service.RestaurantInfoService;
import demo.domain.RestaurantInfo;
import demo.domain.RestaurantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {

    private RestaurantInfoRepository repository;

    @Autowired
    public RestaurantInfoServiceImpl(RestaurantInfoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<RestaurantInfo> saveRestaurantInfos(List<RestaurantInfo> restaurantInfos) {
        return (List<RestaurantInfo>) repository.save(restaurantInfos);
    }

    @Override
    public Page<RestaurantInfo> findByRestaurantName(String name, Pageable pageable) {
        return repository.findByRestaurantName(name, pageable);
    }

    @Override
    public RestaurantInfo findFirstByRestaurantName(String name) {
        return repository.findFirstByRestaurantName(name);
    }

    @Override
    public RestaurantInfo findByRestaurantId(String restaurantId) {
        return repository.findFirstByRestaurantId(restaurantId);
    }
}
