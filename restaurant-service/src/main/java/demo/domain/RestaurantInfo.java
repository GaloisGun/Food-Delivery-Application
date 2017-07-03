package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "RestaurantInfos")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String restaurantId;

    @JsonProperty("restaurantName")
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantCity;
    private String restaurantState;
    private String restaurantZipcode;
    private String restaurantCatalog;

    @JsonProperty("restaurantMenu")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
    private List<MenuItem> restaurantMenu = new ArrayList<MenuItem>();

    public RestaurantInfo(String restaurantName, String address) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = address;
        this.restaurantId = String.valueOf((restaurantName+address).hashCode());
    }

    public RestaurantInfo(String restaurantName, String restaurantAddress, String restaurantId) {
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.restaurantAddress = restaurantAddress;
    }
}
