package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserEntity {

    private Long id;

    private String email;

    private String salt;

    private String hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;

}
