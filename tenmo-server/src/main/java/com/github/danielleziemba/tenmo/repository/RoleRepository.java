package com.github.danielleziemba.tenmo.repository;

import com.github.danielleziemba.tenmo.model.ERole;
import com.github.danielleziemba.tenmo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
