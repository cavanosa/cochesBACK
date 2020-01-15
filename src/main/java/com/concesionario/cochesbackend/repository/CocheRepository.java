package com.concesionario.cochesbackend.repository;

import com.concesionario.cochesbackend.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CocheRepository extends JpaRepository<Coche, Long>,
        JpaSpecificationExecutor<Coche> {
}
