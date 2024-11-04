package com.encode_initiative.icetomeetyoukafkabroker.controller;

import com.encode_initiative.icetomeetyoukafkabroker.model.Order;
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
    public String createOrder(@RequestBody Order order) {
        producer.sendOrderCreatedEvent(order);
        return "Order created event sent";
    }

    // order is sent in via a redis cache when the order is grabbed for the first time.
    @PutMapping("/update")
    public String updateOrder(@RequestBody Order order) {
        producer.sendOrderUpdatedEvent(order);
        return "Order updated event sent";
    }

    @DeleteMapping("/delete")
    public String deleteOrder(@RequestBody Order order) {
        producer.sendOrderDeletedEvent(order);
        return "Order deleted event sent";
    }

    // the user here will be added to the request body using the cached user data when
    // the user logs in for the first time (we will be talking more about redis in the
    // front end chapter...) on the front end allowing us not to have to perform a
    // get lookup in a kafka topic which can cause increased latency which is bad.
    @PostMapping("/assign")
    public String assignOrder(@RequestBody Order order) {
        producer.sendOrderAssignedEvent(order, order.getAssignedUser());
        return "Order assigned event sent";
    }

    @PostMapping("/update/status")
    public String orderStatusChanged(@RequestBody Order order, @RequestParam String newStatus) {
        producer.sendOrderStatusChangedEvent(order, newStatus);
        return "Order status update event sent";
    }
}
