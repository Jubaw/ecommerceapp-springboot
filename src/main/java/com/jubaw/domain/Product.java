package com.jubaw.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @NotBlank(message = "Product name cannot be empty")
    @NotNull(message = "Product name cannot be null")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Brand  cannot be empty")
    @NotNull(message = "Brand  cannot be null")
    @Column(nullable = false)
    private String brand;


    @NotNull(message = "Price  cannot be null")
    @Column(nullable = false)
    private Double price;

}
