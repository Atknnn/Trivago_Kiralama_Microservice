package com.atakan.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 1. Adım gerekli tanımlamalar,
     * 2. Excahange Türü seçimi
     * 3. Kuyruk oluşturma
     * 4. Kuyruk ile Exchange i bir birine bağlama
     */
    private final String EXCHANGE_AUTH = "exchange-auth";
    private final String BIDING_KEY = "key-auth";
    private final String AUTH_QUEUE = "queue-auth-create-user";
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_AUTH);
    }
    @Bean
    Queue queueAuthCreateUser(){
        return new Queue(AUTH_QUEUE);
    }
    @Bean
    public Binding bindingCreateUser(final DirectExchange directExchange, final Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(BIDING_KEY);
    }

}
