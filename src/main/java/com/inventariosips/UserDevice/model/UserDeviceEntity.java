package com.inventariosips.UserDevice.model;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_device")
public class UserDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserDevice;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_USERDEVICE_USER"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_device", nullable = false, foreignKey = @ForeignKey(name = "FK_USERDEVICE_DEVICE"))
    private DeviceEntity device;

    @Column(nullable = false)
    private LocalDateTime assignmentDate;

    private LocalDateTime deliveryDate;

    private String status;

}
