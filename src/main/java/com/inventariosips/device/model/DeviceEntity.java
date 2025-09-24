package com.inventariosips.device.model;

import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.warranty.model.WarrantyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevice;

    @Column(nullable = false, length = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_BRAND"))
    private BrandEntity brand;

    @Column(nullable = false, length = 80)
    private String description;

    @Column(nullable = false, length = 30)
    private String deviceType;

    @ManyToOne
    @JoinColumn(name = "id_assigned_user", foreignKey = @ForeignKey(name = "FK_DEVICE_USER"))
    private UserEntity assignedUser;

    @ManyToOne
    @JoinColumn(name = "id_warranty", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_WARRANTY"))
    private WarrantyEntity warranty;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "id_status_device", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_STATUSDEVICE"))
    private StatusDeviceEntity statusDevice;

    @Column
    private byte[] lifecycleFile;
}
