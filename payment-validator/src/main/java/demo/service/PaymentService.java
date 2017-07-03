package demo.service;


import demo.model.PaymentInfo;

public interface PaymentService {
    boolean validateInfo(PaymentInfo paymentInfo);
    boolean processPaymentInfo(PaymentInfo paymentInfo);
}
