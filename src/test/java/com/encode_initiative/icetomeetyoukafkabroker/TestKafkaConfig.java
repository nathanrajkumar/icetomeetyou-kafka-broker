package com.encode_initiative.icetomeetyoukafkabroker;

import com.encode_initiative.icetomeetyoukafkabroker.consumer.OrderEventConsumer;
import com.encode_initiative.icetomeetyoukafkabroker.model.Order;
import com.encode_initiative.icetomeetyoukafkabroker.producer.OrderEventProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import java.util.concurrent.TimeUnit;

import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
public class TestKafkaConfig {
    @Autowired
    private OrderEventConsumer consumer;

    @Autowired
    private OrderEventProducer producer;

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithProducerToOrderCreatedEvent_thenMessageReceivedByConsumer()
            throws Exception {
        Order order = new Order();
        order.setStatus("NEW ORDER");
        order.setIceCubeOrderAmount(5);
        order.setSendToLocation("SAHARA DESERT");


        producer.sendOrderCreatedEvent(order);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        Assertions.assertEquals(consumer.getPayload().value(), order);
        consumer.resetLatch();
    }
}
