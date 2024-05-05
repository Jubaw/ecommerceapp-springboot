package com.jubaw.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Fetch;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Customer cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


}
