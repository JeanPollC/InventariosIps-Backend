package com.inventariosips.device.model;

import com.inventariosips.area.model.AreaEntity;
import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.warranty.model.WarrantyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Area;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevice;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private AreaEntity area;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_BRAND"))
    private BrandEntity brand;

    @Column(nullable = false, length = 30)
    private String deviceType;

    @Column(length = 50)
    private String storage;

    @Column(name = "graphics_card", length = 100)
    private String graphics_card;

    @Column(length = 50)
    private String ram;

    @Column(length = 100)
    private String processor;

    @Column(name = "product_code", nullable = false, unique = true, length = 100)
    private String product_code;

    @Column(name = "serial_no", nullable = false, unique = true, length = 100)
    private String serial_no;

    @Column(name = "windows_edition", length = 100)
    private String windows_edition;

    @ManyToOne
    @JoinColumn(name = "id_status_device", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_STATUSDEVICE"))
    private StatusDeviceEntity statusDevice;

    @ManyToOne
    @JoinColumn(name = "id_warranty", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_WARRANTY"))
    private WarrantyEntity warranty;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @Lob
    @Column(name = "lifecycle_file")
    private byte[] lifecycleFile;
}
