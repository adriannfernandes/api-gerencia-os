package com.api.gerenciaos.services;


import com.api.gerenciaos.domain.Technician;
import com.api.gerenciaos.dto.technician.TechnicianDto;
import com.api.gerenciaos.exceptions.TechnicianAlreadyExists;
import com.api.gerenciaos.exceptions.TechnicianNotFoundExeception;
import com.api.gerenciaos.repositories.TechnicianRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TechnicianService {

    @Autowired
    private final TechnicianRepository technicianRepository;


    public List<Technician> findAllTech(){
        return technicianRepository.findAll();
    }

    public Technician findById(Long id) throws TechnicianNotFoundExeception {
        return technicianRepository.findById(id)
                .orElseThrow(()-> new TechnicianNotFoundExeception("Technician not found!"));
    }


    @Transactional
    public TechnicianDto saveTech(TechnicianDto technicianDto) throws TechnicianAlreadyExists {
        Optional<Technician> optionalTechnician = technicianRepository.findByCpf(technicianDto.getCpf());

        if(optionalTechnician.isPresent()){
            throw new TechnicianAlreadyExists("Technician already exists");
        }

        Technician technician = new Technician();

        BeanUtils.copyProperties(technicianDto, technician);

        technician.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        technicianRepository.save(technician);

        return ResponseEntity.ok(technicianDto).getBody();
    }
}
