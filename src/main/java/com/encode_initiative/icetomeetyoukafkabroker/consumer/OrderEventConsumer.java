package com.encode_initiative.icetomeetyoukafkabroker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class OrderEventConsumer {
    private CountDownLatch latch = new CountDownLatch(1);
    private ConsumerRecord<?, ?> payload;
    @KafkaListener(topics = "order-created", groupId = "order-management-consumer-group")
    public void consumeOrderCreated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Created Event: " + consumerRecord.toString());
        payload = consumerRecord;
        latch.countDown();
        // Process order creation logic here
    }

    @KafkaListener(topics = "order-updated", groupId = "order-management-consumer-group")
    public void consumeOrderUpdated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Updated Event: " + consumerRecord.toString());
        latch.countDown();
        // Process order update logic here
    }

    @KafkaListener(topics = "order-deleted", groupId = "order-management-consumer-group")
    public void consumeOrderDeleted(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Deleted Event: " + consumerRecord.toString());
        latch.countDown();
        // Process order deletion logic here
    }

    @KafkaListener(topics = "order-assigned", groupId = "order-management-consumer-group")
    public void consumeOrderAssigned(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Assigned Event: " + consumerRecord.toString());
        latch.countDown();
        // Process order assignment logic here
    }

    @KafkaListener(topics = "order-completed", groupId = "order-management-consumer-group")
    public void consumeOrderCompleted(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Completed Event: " + consumerRecord.toString());
        latch.countDown();
        // Process order completion logic here
    }

    @KafkaListener(topics = "user-updated", groupId = "order-management-consumer-group")
    public void consumeUserUpdated(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received User Updated Event: " + consumerRecord.toString());
        latch.countDown();
        // Process user update logic here
    }

    @KafkaListener(topics = "order-comment-added", groupId = "order-management-consumer-group")
    public void consumeOrderCommentAdded(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Received Order Comment Added Event: " + consumerRecord.toString());
        latch.countDown();
        // Process order comment addition logic here
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public ConsumerRecord<?, ?> getPayload() {
        return this.payload;
    }

}
