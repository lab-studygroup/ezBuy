package com.ezbuy.customer.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name must be at most 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Customer email is required")
    @Size(max = 100, message = "Customer email must be at most 100 characters")
    @Email(message = "Customer email is not valid")
    @Column(nullable = false, unique = true)
    private String email;
}

