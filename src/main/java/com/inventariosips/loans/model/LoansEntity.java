package com.inventariosips.loans.model;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "loans")
@Table(
        name = "loans",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_LOAN_USER_DEVICE_STARTDATE",
                        columnNames = {"id_user", "id_device", "start_date_loan"}
                )
        }
)
public class LoansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoans;


    @Column(nullable = false)
    private LocalDateTime startDateLoan;

    @Column(nullable = false)
    private LocalDateTime endDateLoan;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_LOAN_USER"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_device", nullable = false, foreignKey = @ForeignKey(name = "FK_LOAN_DEVICE"))
    private DeviceEntity device;

    @Column
    private byte[] loanDocument;
}
