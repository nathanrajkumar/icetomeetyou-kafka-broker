package com.encode_initiative.icetomeetyoukafkabroker.model;

import lombok.Data;

@Data
public class Order {
    private Long orderId;
    private User assignedUser;
    private String status;
    private Integer iceCubeOrderAmount;
    private String sendToLocation;
}
