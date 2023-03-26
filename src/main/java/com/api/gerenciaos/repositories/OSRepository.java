package com.api.gerenciaos.repositories;

import com.api.gerenciaos.domain.OS;
import com.api.gerenciaos.domain.enums.TypeEquipment;
import com.api.gerenciaos.domain.enums.TypeOs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface OSRepository extends JpaRepository<OS, Long> {

    @Query("select o.id from OS o where o.owner.id = ?1")
    List<Object> findAllByClient(Long id);

    @Query("select o from OS o where o.typeOs = ?1")
    List<Object> findAllByType(TypeOs typeOs);

    @Query("select o from OS o where o.typeEquipment = ?1")
    List<Object> findAllByEquipment(TypeEquipment typeEquipment);

    @Query("select o from OS o where o.registrationDate between ?1 AND ?2 ")
    List<Object> findAllByRegistrationDateBetween(LocalDate inicialDate, LocalDate finalDate);
}
