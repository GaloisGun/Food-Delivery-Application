package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class PaymentSource {

    @Autowired
    private MessageChannel output;

    @RequestMapping(path = "/api/payments", method = RequestMethod.POST)
    public void payments(@RequestBody String paymentInfo) {
        this.output.send(MessageBuilder.withPayload(paymentInfo).build());
    }
}
