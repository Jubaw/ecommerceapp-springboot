package com.jubaw.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Lastname cannot be empty")
    @NotNull(message = "Lastname cannot be null")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @Column(nullable = false, unique = true)
    @Email
    private String email;


    @Column
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    @JsonIgnore
    @OneToMany(mappedBy = "customer", orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<OrderItem> orders;


}
