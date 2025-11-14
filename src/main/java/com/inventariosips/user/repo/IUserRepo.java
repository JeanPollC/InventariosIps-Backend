package com.inventariosips.user.repo;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM user_entity u " +
            "WHERE LOWER(u.name) LIKE %:filter% OR " +
            "LOWER(u.lastName) LIKE %:filter% OR " +
            "LOWER(u.email) LIKE %:filter% OR " +
            "LOWER(u.userType.userType) LIKE %:filter% OR " +
            "LOWER(u.status) LIKE %:filter%")
    Page<UserEntity> findByGlobalFilter(@Param("filter") String filter, Pageable pageable);
}
