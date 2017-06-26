package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {

    private String itemName;
    private String itemDescription;
    private String itemCatalog;
    private double itemPrice;

    public MenuItem(){}

    @JsonCreator
    public MenuItem(@JsonProperty("itemName")   String itemName,
                    @JsonProperty("itemDescription")    String itemDescription,
                    @JsonProperty("itemCatalog")    String itemCatalog,
                    @JsonProperty("itemPrice")  double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCatalog = itemCatalog;
        this.itemPrice = itemPrice;
    }
}
