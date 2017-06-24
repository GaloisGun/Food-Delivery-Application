package demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;

/**
 * Created by wuyufei on 6/24/17.
 */
@Data
public class OrderInfo {
    @Id
    private Long id;
}
