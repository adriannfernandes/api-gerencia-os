package com.api.gerenciaos.repositories;

import com.api.gerenciaos.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Optional<Technician> findByCpf(String cpf);

}
