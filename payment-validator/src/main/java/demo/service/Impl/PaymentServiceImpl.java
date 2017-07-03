package demo.service.Impl;

import demo.model.OrderInfo;
import demo.model.OrderStatus;
import demo.model.PaymentInfo;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean validateInfo(PaymentInfo paymentInfo) {
        int m = 3;
        boolean isValid = (((paymentInfo.getCardNumber() + paymentInfo.getExpiration()).hashCode() - paymentInfo.getCode().hashCode())%m == 0);
        return isValid;
    }

    @Override
    public boolean processPaymentInfo(PaymentInfo paymentInfo) {

        String prefix = "http://localhost:9002/restaurant/order/";
        String url = prefix + paymentInfo.getOrderId();
        boolean isValid = validateInfo(paymentInfo);
        if (isValid) {
            log.info("Payment validated, calling order-service API to show confirmed info");

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderStatus(OrderStatus.Paid);

            HttpEntity<OrderInfo> entity = new HttpEntity<>(orderInfo);
            this.restTemplate.put("http://localhost:9002/restaurant/order/{orderId}", entity, paymentInfo.getOrderId());
        }
        return isValid;
    }
}
