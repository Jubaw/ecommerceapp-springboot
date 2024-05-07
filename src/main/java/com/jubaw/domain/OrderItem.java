package com.jubaw.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Quantity cannot be null")
    @Column(nullable = false)
    private Integer quantity;


    @NotNull(message = "Price cannot be null")
    @Column(nullable = false)
    private Double totalPrice;

    @NotNull(message = "Product cannot be null")
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Product product;

    @NotNull(message = "Customer cannot be null")
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Customer customer;


}
