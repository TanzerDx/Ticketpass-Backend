package com.example.individual_assignment_hristo_ganchev.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.valueextraction.UnwrapByDefault;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String encodedPassword;

    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "role"))
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleEntity> roles;

}
