package com.stackroute.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class IdeaHamsterRabbitMqConfig {

    @Value("${ih.rabbitmq.queue}")
    String queueName;

    @Value("${ih.rabbitmq.exchange}")
    String exchange;

    @Value("${ih.rabbitmq.routingkey}")
    String routingkey;
    /*.....................queue--------------*/
//    @Value("${ihPostedIdea.rabbitmq.queue}")
//    String postedIdeaQueueName;
//    @Value("${ihPostedIdea.rabbitmq.exchange}")
//    String postedIdeaprofileExchange;
//    @Value("${ihPostedIdea.rabbitmq.routingkey}")
//    String postedIdeaRoutingkey;
//    @Bean
//    Queue queueWorkedIdea() {
//        return new Queue(postedIdeaQueueName, true);
//    }
//    @Bean
//    TopicExchange exchangeWorkedIdea() {
//        return new TopicExchange(postedIdeaprofileExchange);
//    }
//    @Bean
//    Binding bindingWorkedIdea(Queue queueWorkedIdea, TopicExchange exchangeWorkedIdea) {
//        return  BindingBuilder.bind(queueWorkedIdea).to(exchangeWorkedIdea).with(postedIdeaRoutingkey);
//    }

    /* -----------------profile------------------- */
    @Value("${ihProfile.rabbitmq.queue}")
    String profileQueueName;
    @Value("${ihProfile.rabbitmq.exchange}")
    String profileExchange;
    @Value("${ihProfile.rabbitmq.routingkey}")
    String profilRoutingkey;
    @Bean
    Queue queueProfile() {
        return new Queue(profileQueueName, true);
    }
    @Bean
    TopicExchange exchangeProfile() {
        return new TopicExchange(profileExchange);
    }
    @Bean
    Binding bindingProfile(Queue queueProfile, TopicExchange exchangeProfile) {
        return  BindingBuilder.bind(queueProfile).to(exchangeProfile).with(profilRoutingkey);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}