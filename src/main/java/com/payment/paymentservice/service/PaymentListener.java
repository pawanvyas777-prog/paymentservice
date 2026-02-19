package com.payment.paymentservice.service;

import com.payment.paymentservice.dto.BookingMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentListener {

    private final RabbitTemplate rabbitTemplate;

    public PaymentListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "payment.queue")
    public void processPayment(BookingMessage message) throws InterruptedException {

        System.out.println("Payment Service received: " + message);


        // Simulate payment processing delay
        Thread.sleep(2000);

        System.out.println("Payment Successful for: " + message);

        // Send success event back
        rabbitTemplate.convertAndSend("booking_exchange",
                "payment.success",
                message);
    }
}
