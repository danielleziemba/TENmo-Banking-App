package com.github.danielleziemba.tenmo.repository;

import com.github.danielleziemba.tenmo.model.TransferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferTypeRepository extends JpaRepository<TransferType, Long> {
}
