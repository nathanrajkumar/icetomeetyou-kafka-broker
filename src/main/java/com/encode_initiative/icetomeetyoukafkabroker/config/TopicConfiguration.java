package com.encode_initiative.icetomeetyoukafkabroker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.encode_initiative.icetomeetyoukafkabroker.Constants.*;


/*
    NewTopic: This class is used to define a topic. It takes three arguments:
        Topic Name: The name of the topic.
        Partitions: The number of partitions for the topic (set to 1 for simplicity, but you can increase it if you need better parallelism).
        Replication Factor: Set to 1 here, but in production, you may set it higher for redundancy.
*/

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic(ORDER_CREATED_TOPIC_NAME, 1, (short) 1);
    }

    @Bean
    public NewTopic orderUpdatedTopic() {
        return new NewTopic(ORDER_UPDATED_TOPIC_NAME, 1, (short) 1);
    }

    @Bean
    public NewTopic orderDeletedTopic() {
        return new NewTopic(ORDER_DELETED_TOPIC_NAME, 1, (short) 1);
    }

    @Bean
    public NewTopic orderAssignedTopic() {
        return new NewTopic(ORDER_ASSIGNED_TO_USER_TOPIC_NAME, 1, (short) 1);
    }

    @Bean
    public NewTopic orderStatusChangedTopic() {
        return new NewTopic(ORDER_STATUS_CHANGED_TOPIC_NAME, 1, (short) 1);
    }

    @Bean
    public NewTopic userUpdatedTopic() {
        return new NewTopic(USER_UPDATED_TOPIC_NAME, 1, (short) 1);
    }
}
