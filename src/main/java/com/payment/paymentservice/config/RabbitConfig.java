package com.payment.paymentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Same exchange name as Seat Service
    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange("booking_exchange");
    }

    // Queue where Payment Service receives payment requests
    @Bean
    public Queue paymentQueue() {
        return new Queue("payment.queue", true);
    }

    // Bind payment.queue to exchange using SAME routing key
    @Bean
    public Binding paymentBinding(Queue paymentQueue, TopicExchange bookingExchange) {
        return BindingBuilder
                .bind(paymentQueue)
                .to(bookingExchange)
                .with("payment.request");
    }

    // Queue where Seat Service listens for success
    @Bean
    public Queue paymentSuccessQueue() {
        return new Queue("payment_success_queue", true);
    }

    @Bean
    public Binding successBinding(Queue paymentSuccessQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(paymentSuccessQueue)
                .to(exchange)
                .with("payment.success");
    }


    @Bean
    public JacksonJsonMessageConverter converter() {
        return new JacksonJsonMessageConverter("*");
    }


    // Attach JSON converter to RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}