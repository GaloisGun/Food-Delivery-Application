package demo.controller;

import demo.Service.RestaurantInfoService;
import demo.domain.RestaurantInfo;
import demo.domain.RestaurantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantBulkUploadController {

    private RestaurantInfoRepository repository;
    private RestaurantInfoService service;


    @Autowired
    public RestaurantBulkUploadController(RestaurantInfoRepository repository, RestaurantInfoService service){
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(value = "/bulk/restaurantInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RestaurantInfo> restaurantInfos){
        this.repository.save(restaurantInfos);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void purge() {this.repository.deleteAll();}

    @RequestMapping(value = "/restaurantInfo/{restaurantName}", method = RequestMethod.GET)
    public ResponseEntity<?> findRestaurantInfoByName(@PathVariable("restaurantName") String restaurantName) {
        RestaurantInfo restaurantInfo = this.service.findByRestaurantName(restaurantName);
        if (restaurantInfo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurantInfo);
    }


}
