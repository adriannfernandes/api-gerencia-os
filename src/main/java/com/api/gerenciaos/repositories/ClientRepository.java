package com.api.gerenciaos.repositories;

import com.api.gerenciaos.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.emailAddress = ?1")
    Optional<Client> findByEmail(String emailAddress);


}
