package com.inventariosips.userType.repo;

import com.inventariosips.userType.model.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTypeRepo extends JpaRepository<UserTypeEntity, Integer> {
}
