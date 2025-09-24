package com.inventariosips.loans.repo;

import com.inventariosips.loans.model.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoansRepo extends JpaRepository<LoansEntity, Integer> {
}
