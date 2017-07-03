package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "MenuItem")
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
    private Long Id;

    private String itemId;
    private String itemName;
    private String itemDescription;
    private String itemCatalog;
    private double itemPrice;
    //private double itemTax;

    private String restaurantId;

    @ManyToOne
    private RestaurantInfo restaurantInfo;


    public MenuItem(String name, double price, String description, String restaurantId) {
        this.itemName = name;
        this.itemPrice = price;
        this.itemDescription = description;
        this.restaurantId = restaurantId;
        this.itemId = String.valueOf((name+price+description).hashCode());
    }


}
