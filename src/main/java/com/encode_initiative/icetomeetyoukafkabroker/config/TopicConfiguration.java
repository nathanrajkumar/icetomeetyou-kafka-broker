package com.encode_initiative.icetomeetyoukafkabroker.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/*
    NewTopic: This class is used to define a topic. It takes three arguments:
        Topic Name: The name of the topic.
        Partitions: The number of partitions for the topic (set to 1 for simplicity, but you can increase it if you need better parallelism).
        Replication Factor: Set to 1 here, but in production, you may set it higher for redundancy.
*/

@Configuration
public class TopicConfiguration {

    @Value("${spring.kafka.template.order-created-topic-name}")
    private String orderCreatedTopicName;
    @Value("${spring.kafka.template.order-updated-topic-name}")
    private String orderUpdatedTopicName;
    @Value("${spring.kafka.template.order-deleted-topic-name}")
    private String orderDeletedTopicName;
    @Value("${spring.kafka.template.order-assigned-to-user-topic-name}")
    private String orderAssignedToUserTopicName;
    @Value("${spring.kafka.template.order-completed-topic-name}")
    private String orderCompletedTopicName;
    @Value("${spring.kafka.template.user-updated-topic-name}")
    private String userUpdatedTopicName;
    @Value("${spring.kafka.template.order-comment-topic-name}")
    private String orderCommentTopicName;

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic(orderCreatedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderUpdatedTopic() {
        return new NewTopic(orderUpdatedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderDeletedTopic() {
        return new NewTopic(orderDeletedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderAssignedTopic() {
        return new NewTopic(orderAssignedToUserTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderCompletedTopic() {
        return new NewTopic(orderCompletedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic userUpdatedTopic() {
        return new NewTopic(userUpdatedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderCommentAddedTopic() {
        return new NewTopic(orderCommentTopicName, 1, (short) 1);
    }
}
