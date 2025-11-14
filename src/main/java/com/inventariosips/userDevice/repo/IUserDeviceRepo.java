package com.inventariosips.userDevice.repo;


import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDeviceRepo extends JpaRepository<UserDeviceEntity, Integer> {

    @Query("SELECT ud FROM user_device ud " +
            "WHERE LOWER(ud.user.name) LIKE %:filter% OR " +
            "LOWER(ud.device.name) LIKE %:filter% OR " +
            "LOWER(FUNCTION('FORMAT', ud.assignmentDate, 'yyyy-MM-dd')) LIKE %:filter% OR " +
            "LOWER(FUNCTION('FORMAT', ud.deliveryDate, 'yyyy-MM-dd')) LIKE %:filter% OR " +
            "LOWER(ud.status) LIKE %:filter%")
    Page<UserDeviceEntity> findByGlobalFilter(@Param("filter") String filter, Pageable pageable);
}
