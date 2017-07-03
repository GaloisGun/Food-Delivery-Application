package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.PaymentInfo;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
@EnableBinding(Sink.class)
@Slf4j
public class PaymentValidatorSink {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentService paymentService;

    public void updatePaymentInfos(String input) throws Exception {
        log.info("Payment INFO: " + input);
        PaymentInfo paymentInfo = this.objectMapper.readValue(input, PaymentInfo.class);
        boolean isValid = paymentService.processPaymentInfo(paymentInfo);
        log.info("Payment processed, valid payment: " + isValid);
    }

    public void updatePaymentInfoFallback(String input) {
        log.info("Unable to process payment" + input);
    }

}
