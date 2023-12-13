package com.example.individual_assignment_hristo_ganchev.business.Notifications;

import com.example.individual_assignment_hristo_ganchev.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class NotificationMessage {
    private Long id;
    private String from;
    private String topic;
    private String text;

}
