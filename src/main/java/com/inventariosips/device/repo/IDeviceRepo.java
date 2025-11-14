package com.inventariosips.device.repo;

import com.inventariosips.device.model.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeviceRepo extends JpaRepository<DeviceEntity, Integer> {

    @Query(value = """
                select concat(u.name, ' ', u.last_name) as nameUser
            	from user_device ud
            	inner join user_entity u on u.id_user = ud.id_user
            	inner join device d on ud.id_device = d.id_device
            	where d.name = :deviceName
                AND (
                    ud.delivery_date IS NULL
                    OR ud.delivery_date > NOW()
                )
            """, nativeQuery = true)
    String getNameUserByNameDevice(@Param("deviceName") String deviceName);

    @Query(value = """
                select concat(u.name, ' ', u.last_name) as nameUser
            	from loans ud
            	inner join user_entity u on u.id_user = ud.id_user
            	inner join device d on ud.id_device = d.id_device
            	where d.name = :deviceName
                AND (
                    ud.end_date_loan IS NULL
                    OR ud.end_date_loan > NOW()
                )
            """, nativeQuery = true)
    String getNameUserByNameDeviceLoan(@Param("deviceName") String deviceName);

    @Query("SELECT d FROM device d " +
            "WHERE LOWER(d.name) LIKE %:filter% OR " +
            "LOWER(d.deviceType) LIKE %:filter% OR " +
            "LOWER(d.product_code) LIKE %:filter% OR " +
            "LOWER(d.serial_no) LIKE %:filter% OR " +

            // Relaciones (Campos visibles en la tabla o importantes para búsqueda)
            "LOWER(d.area.nameArea) LIKE %:filter% OR " +
            "LOWER(d.statusDevice.nameStatus) LIKE %:filter% OR " +
            "LOWER(d.brand.description) LIKE %:filter% OR " +

            // Otros campos de detalle relevantes
            "LOWER(d.storage) LIKE %:filter% OR " +
            "LOWER(d.ram) LIKE %:filter% OR " +
            "LOWER(d.processor) LIKE %:filter% OR " +
            "LOWER(d.observation) LIKE %:filter%")
    Page<DeviceEntity> findByGlobalFilter(@Param("filter") String filter, Pageable pageable);

    List<DeviceEntity> findByStatusDevice_NameStatus(String nameStatus);

}
