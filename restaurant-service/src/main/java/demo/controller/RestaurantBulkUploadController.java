package demo.controller;

import demo.Service.RestaurantInfoService;
import demo.domain.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantBulkUploadController {

    //private RestaurantInfoRepository repository;
    private RestaurantInfoService service;


    @Autowired
    public RestaurantBulkUploadController(RestaurantInfoService service){
        //this.repository = repository;
        this.service = service;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<RestaurantInfo> upload(@RequestBody List<RestaurantInfo> restaurantInfos){
        return this.service.saveRestaurantInfos(restaurantInfos);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.DELETE)
    public void purge() {this.service.deleteAll();}

    @RequestMapping(value = "/restaurant/{restaurantName}/list", method = RequestMethod.GET)
    public Page<RestaurantInfo> findByRestaurantName(@PathVariable String restaurantName, Pageable pageable){
        return this.service.findByRestaurantName(restaurantName, pageable);
    }

    @RequestMapping(value = "/restaurant/{restaurantName}", method = RequestMethod.GET)
    public RestaurantInfo findByRestaurantName(@PathVariable String restaurantName) {
        return this.service.findFirstByRestaurantName(restaurantName);
    }

}
