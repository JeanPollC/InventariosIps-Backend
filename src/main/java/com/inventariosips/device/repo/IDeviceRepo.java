package com.inventariosips.device.repo;

import com.inventariosips.device.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepo extends JpaRepository<DeviceEntity, Integer> {

    @Query(value = """
                select concat(u.name, ' ', u.last_name) as nameUser
            	from user_device ud
            	inner join user_entity u on u.id_user = ud.id_user
            	inner join device d on ud.id_device = d.id_device
            	where d.name = :deviceName
            """, nativeQuery = true)
    String getNameUserByNameDevice(@Param("deviceName") String deviceName);
}
