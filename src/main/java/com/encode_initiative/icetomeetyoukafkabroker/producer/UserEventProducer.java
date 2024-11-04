package com.encode_initiative.icetomeetyoukafkabroker.producer;

import com.encode_initiative.icetomeetyoukafkabroker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.encode_initiative.icetomeetyoukafkabroker.Constants.USER_UPDATED_TOPIC_NAME;

@Service
public class UserEventProducer {

    private final KafkaTemplate<Long, User> kafkaTemplate;

    @Autowired
    public UserEventProducer(KafkaTemplate<Long, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserUpdatedEvent(User user) {
        kafkaTemplate.send(USER_UPDATED_TOPIC_NAME, user.getUserId(), user);
    }

}
