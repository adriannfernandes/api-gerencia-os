package com.api.gerenciaos.repositories;

import com.api.gerenciaos.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {



}
