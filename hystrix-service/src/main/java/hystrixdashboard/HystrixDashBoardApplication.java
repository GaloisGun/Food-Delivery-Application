package hystrixdashboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class HystrixDashBoardApplication {

    @RequestMapping("/")
    public String home() { return "foward:/hystrix"; }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixDashBoardApplication.class).run(args);
    }
}
