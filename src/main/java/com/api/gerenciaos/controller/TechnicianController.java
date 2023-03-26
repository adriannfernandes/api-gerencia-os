package com.api.gerenciaos.controller;


import com.api.gerenciaos.domain.Technician;

import com.api.gerenciaos.dto.technician.TechnicianDto;
import com.api.gerenciaos.exceptions.TechnicianAlreadyExists;
import com.api.gerenciaos.exceptions.TechnicianNotFoundExeception;
import com.api.gerenciaos.services.TechnicianService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("api/tech")
@RequiredArgsConstructor
public class TechnicianController {

    @Autowired
    private final TechnicianService technicianService;

    @GetMapping
    public ResponseEntity<List<Technician>> findAllTech(){
        return ResponseEntity.ok(technicianService.findAllTech());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technician> findTechById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(technicianService.findById(id));
        }catch (TechnicianNotFoundExeception error){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<TechnicianDto> saveTech(@RequestBody @Valid TechnicianDto technicianDto){
            try {
                return new ResponseEntity(technicianService.saveTech(technicianDto), HttpStatus.CREATED);
            }catch (TechnicianAlreadyExists erro){
                throw new ResponseStatusException(HttpStatus.CONFLICT, erro.getMessage());
            }

    }




}
