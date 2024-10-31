package com.encode_initiative.icetomeetyoukafkabroker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(String orderId, String orderDetails) {
        kafkaTemplate.send("order-created", orderId, orderDetails);
    }

    public void sendOrderUpdatedEvent(String orderId, String orderDetails) {
        kafkaTemplate.send("order-updated", orderId, orderDetails);
    }

    public void sendOrderDeletedEvent(String orderId) {
        kafkaTemplate.send("order-deleted", orderId, "Order deleted");
    }

    public void sendOrderAssignedEvent(String orderId, String userThatOrderIsAssignedTo) {
        kafkaTemplate.send("order-assigned-to-user", orderId, userThatOrderIsAssignedTo);
    }

    public void sendOrderCompletedEvent(String orderId) {
        kafkaTemplate.send("order-completed", orderId, "Order completed");
    }

    public void sendUserUpdatedEvent(String userId, String userDetails) {
        kafkaTemplate.send("user-updated", userId, userDetails);
    }

    public void sendOrderCommentAddedEvent(String orderId, String comment) {
        kafkaTemplate.send("order-comment-added", orderId, comment);
    }
}
