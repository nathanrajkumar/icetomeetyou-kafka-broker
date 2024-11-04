package com.encode_initiative.icetomeetyoukafkabroker.controller;

import com.encode_initiative.icetomeetyoukafkabroker.model.User;
import com.encode_initiative.icetomeetyoukafkabroker.producer.UserEventProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserEventProducer userEventProducer;

    public UserController(UserEventProducer userEventProducer) {
        this.userEventProducer = userEventProducer;
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestBody User user) {
        userEventProducer.sendUserUpdatedEvent(user);
        return "User updated event sent";
    }
}
