package com.encode_initiative.icetomeetyoukafkabroker.consumer;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

import static com.encode_initiative.icetomeetyoukafkabroker.Constants.*;

@Getter
@Service
public class OrderEventConsumer {
    private CountDownLatch latch = new CountDownLatch(1);
    private ConsumerRecord<?, ?> payload;
    @KafkaListener(topics = ORDER_CREATED_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeOrderCreated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order Created Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        payload = consumerRecord;
        latch.countDown();
        // Process order creation logic here
    }

    @KafkaListener(topics = ORDER_UPDATED_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeOrderUpdated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order Updated Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        latch.countDown();
        // Process order update logic here
    }

    @KafkaListener(topics = ORDER_DELETED_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeOrderDeleted(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order Deleted Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        latch.countDown();
        // Process order deletion logic here
    }

    @KafkaListener(topics = ORDER_ASSIGNED_TO_USER_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeOrderAssigned(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order Assigned To User Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        latch.countDown();
        // Process order assignment logic here
    }

    @KafkaListener(topics = ORDER_STATUS_CHANGED_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeOrderStatusChanged(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order Status Changed Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        latch.countDown();
        // Process order completion logic here
    }

    @KafkaListener(topics = USER_UPDATED_TOPIC_NAME, groupId = "order-management-consumer-group")
    public void consumeUserUpdated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.printf("Order User Updated Event Received: Key = %s, Value = %s%n", consumerRecord.key(), consumerRecord.value());
        latch.countDown();
        // Process user update logic here
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}
