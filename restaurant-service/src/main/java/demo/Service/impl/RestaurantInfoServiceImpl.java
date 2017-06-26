package demo.Service.impl;

import demo.Service.RestaurantInfoService;
import demo.domain.RestaurantInfo;
import demo.domain.RestaurantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        this.repository.deleteAll();
    }

    @Override
    public RestaurantInfo findByRestaurantName(String restaurantName) {
        return this.repository.findRestaurantInfoByRestaurantName(restaurantName);
    }
}
