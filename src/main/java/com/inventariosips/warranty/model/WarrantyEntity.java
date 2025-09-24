package com.inventariosips.warranty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warranty")
public class WarrantyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idWarranty;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
}
