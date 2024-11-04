package com.encode_initiative.icetomeetyoukafkabroker.producer;

import com.encode_initiative.icetomeetyoukafkabroker.model.Order;
import com.encode_initiative.icetomeetyoukafkabroker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.encode_initiative.icetomeetyoukafkabroker.Constants.*;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<Long, Order> kafkaTemplate;

    @Autowired
    public OrderEventProducer(KafkaTemplate<Long, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(Order order) {
        kafkaTemplate.send(ORDER_CREATED_TOPIC_NAME, UUID.randomUUID().getMostSignificantBits(), order);
    }

    public void sendOrderUpdatedEvent(Order orderToUpdate) {
        kafkaTemplate.send(ORDER_UPDATED_TOPIC_NAME, orderToUpdate.getOrderId(), orderToUpdate);
    }

    public void sendOrderDeletedEvent(Order orderToBeDeleted) {
        orderToBeDeleted.setStatus("DELETED");
        kafkaTemplate.send(ORDER_DELETED_TOPIC_NAME, orderToBeDeleted.getOrderId(), orderToBeDeleted);
    }

    public void sendOrderStatusChangedEvent(Order changedOrder, String newStatus) {
        changedOrder.setStatus(newStatus);
        kafkaTemplate.send(ORDER_STATUS_CHANGED_TOPIC_NAME, changedOrder.getOrderId(), changedOrder);
    }

    public void sendOrderAssignedEvent(Order order, User userThatOrderIsAssignedTo) {
        order.setAssignedUser(userThatOrderIsAssignedTo);
        kafkaTemplate.send(ORDER_ASSIGNED_TO_USER_TOPIC_NAME, order.getOrderId(), order);
    }
}
