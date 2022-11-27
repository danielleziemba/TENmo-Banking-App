package com.github.danielleziemba.tenmo.repository;

import com.github.danielleziemba.tenmo.model.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferStatusRepository extends JpaRepository<TransferStatus, Long> {
}
