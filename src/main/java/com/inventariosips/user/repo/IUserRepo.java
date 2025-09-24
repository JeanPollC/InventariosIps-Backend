package com.inventariosips.user.repo;

import com.inventariosips.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Integer> {
}
