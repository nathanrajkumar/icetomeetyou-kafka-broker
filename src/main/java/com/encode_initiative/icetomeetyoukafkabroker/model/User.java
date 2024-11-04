package com.encode_initiative.icetomeetyoukafkabroker.model;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private Long assignedOrderId;
}
