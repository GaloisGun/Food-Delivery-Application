package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class RestaurantInfo {

    @Id
    private Long id;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantCity;
    private String restaurantState;
    private String restaurantZipcode;
    private String restaurantCatalog;

    private List<MenuItem> restaurantMenu;

    @JsonCreator
    public RestaurantInfo(@JsonProperty("restaurantName") String restaurantName,
                          @JsonProperty("restaurantAddress") String restaurantAddress,
                          @JsonProperty("restaurantCity") String restaurantCity,
                          @JsonProperty("restaurantState") String restaurantState,
                          @JsonProperty("restaurantZipcode") String restaurantZipcode,
                          @JsonProperty("restaurantCatalog") String restaurantCatalog,
                          @JsonProperty("restaurantMenu") List<MenuItem> restaurantMenu) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantCity = restaurantCity;
        this.restaurantState = restaurantState;
        this.restaurantZipcode = restaurantZipcode;
        this.restaurantCatalog = restaurantCatalog;
        this.restaurantMenu = restaurantMenu;

    }


}
