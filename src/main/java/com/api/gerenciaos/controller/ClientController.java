package com.api.gerenciaos.controller;

import com.api.gerenciaos.domain.Client;
import com.api.gerenciaos.dto.client.ClientDto;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientAlreadyExistsExeception;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientNotFoundExeception;
import com.api.gerenciaos.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<Client>> findAllClients(@PageableDefault Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(clientService.findClientById(id));
        } catch (ClientNotFoundExeception erro){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, erro.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<ClientDto> save(@RequestBody @Valid ClientDto clientDto){
        try{
            return new ResponseEntity<>(clientService.saveClient(clientDto), HttpStatus.CREATED);
        }catch (ClientAlreadyExistsExeception erro){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                             @RequestBody ClientDto clientDto){

        try{
            clientService.updateClient(id, clientDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ClientNotFoundExeception error){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (ClientAlreadyExistsExeception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
        try {
            clientService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ClientNotFoundExeception error){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
