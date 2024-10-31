package com.encode_initiative.icetomeetyoukafkabroker.controller;

import com.encode_initiative.icetomeetyoukafkabroker.producer.OrderEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderEventProducer producer;

    @Autowired
    public OrderController(OrderEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam String orderId, @RequestParam String orderDetails) {
        producer.sendOrderCreatedEvent(orderId, orderDetails);
        return "Order created event sent";
    }

    @PutMapping("/update")
    public String updateOrder(@RequestParam String orderId, @RequestParam String orderDetails) {
        producer.sendOrderUpdatedEvent(orderId, orderDetails);
        return "Order updated event sent";
    }

    @DeleteMapping("/delete")
    public String deleteOrder(@RequestParam String orderId) {
        producer.sendOrderDeletedEvent(orderId);
        return "Order deleted event sent";
    }

    @PostMapping("/assign")
    public String assignOrder(@RequestParam String orderId, @RequestParam String assignee) {
        producer.sendOrderAssignedEvent(orderId, assignee);
        return "Order assigned event sent";
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestParam String orderId) {
        producer.sendOrderCompletedEvent(orderId);
        return "Order completed event sent";
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestParam String userId, @RequestParam String userDetails) {
        producer.sendUserUpdatedEvent(userId, userDetails);
        return "User updated event sent";
    }

    @PostMapping("/comment")
    public String addComment(@RequestParam String orderId, @RequestParam String comment) {
        producer.sendOrderCommentAddedEvent(orderId, comment);
        return "Order comment added event sent";
    }
}
