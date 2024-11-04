package com.encode_initiative.icetomeetyoukafkabroker;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static final String ORDER_CREATED_TOPIC_NAME = "order-created";
    public static final String ORDER_UPDATED_TOPIC_NAME = "order-updated";
    public static final String ORDER_DELETED_TOPIC_NAME = "order-deleted";
    public static final String ORDER_STATUS_CHANGED_TOPIC_NAME = "order-status-changed";
    public static final String ORDER_ASSIGNED_TO_USER_TOPIC_NAME = "order-assigned-to-user";
    public static final String USER_UPDATED_TOPIC_NAME = "user-updated";

}

