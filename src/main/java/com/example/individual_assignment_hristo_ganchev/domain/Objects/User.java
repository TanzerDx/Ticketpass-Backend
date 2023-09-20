package com.example.individual_assignment_hristo_ganchev.domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String salt;

    private String hashedPassword;

    private List<Long> orderList;

    private List<Long> orderListExpired;

}
