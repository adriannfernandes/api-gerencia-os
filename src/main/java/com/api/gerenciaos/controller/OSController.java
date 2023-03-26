package com.api.gerenciaos.controller;

import com.api.gerenciaos.domain.OS;
import com.api.gerenciaos.dto.os.OSDto;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientNotFoundExeception;
import com.api.gerenciaos.exceptions.OSExecptions.OsNotFoundExeception;
import com.api.gerenciaos.exceptions.TechnicianNotFoundExeception;
import com.api.gerenciaos.services.OSService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("api/service-order")
@RequiredArgsConstructor
public class OSController {

    @Autowired
    private final OSService osService;

    @GetMapping
    public ResponseEntity<Page<OS>> findAllOS(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(osService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findOsById(@PathVariable(value = "id") Long id) throws OsNotFoundExeception {
        return ResponseEntity.ok(osService.findById(id));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Object>> findOSByClient(@PathVariable(value = "id") Long id){
        try {
            return ResponseEntity.ok(osService.findOsByClient(id));
        } catch (ClientNotFoundExeception erro){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/typeOs")
    public ResponseEntity<List<Object>> findAllOsByType(@RequestParam(value = "type") String typeOs){
        return ResponseEntity.ok(osService.findAllByType(typeOs));
    }

    @GetMapping("/typeEquipment")
    public ResponseEntity<List<Object>> findAllOsByEquipment(@RequestParam String equipment){
        return ResponseEntity.ok(osService.findAllByEquipment(equipment));
    }
    @GetMapping("/byInterval")
    public ResponseEntity<List<Object>> findAllOsByTimeInterval(@RequestParam String inicialDate, String finalDate) {
        return ResponseEntity.ok(osService.findByDateRange(inicialDate, finalDate));
    }

    @PostMapping
    public ResponseEntity<OSDto> saveOS(@RequestBody @Valid OSDto osDto) {
        try {
            return new ResponseEntity<>(osService.save(osDto), HttpStatus.CREATED);
        } catch (ClientNotFoundExeception error){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage());
        } catch (TechnicianNotFoundExeception error){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage());
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<OS> updateOS(@PathVariable(value = "id") Long id,
                                       @RequestBody @Valid OSDto osDto)  {
        try {
            osService.update(id, osDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OsNotFoundExeception erro){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (ClientNotFoundExeception erro){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TechnicianNotFoundExeception erro){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OS> deleteOS(@PathVariable(value = "id") Long id) {
        try {
            osService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (OsNotFoundExeception erro){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
