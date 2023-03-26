package com.api.gerenciaos.services;


import com.api.gerenciaos.domain.OS;
import com.api.gerenciaos.domain.enums.TypeEquipment;
import com.api.gerenciaos.domain.enums.TypeOs;
import com.api.gerenciaos.dto.os.OSDto;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientNotFoundExeception;
import com.api.gerenciaos.exceptions.OSExecptions.OsNotFoundExeception;
import com.api.gerenciaos.exceptions.TechnicianNotFoundExeception;
import com.api.gerenciaos.repositories.OSRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OSService {

    @Autowired
    private final OSRepository osRepository;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final TechnicianService technicianService;

    public Page<OS> findAll(Pageable pageable){
        return osRepository.findAll(pageable);
    }

    public OS findById(Long id) throws OsNotFoundExeception {
        return osRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "OS not Found"));
    }

    public List<Object> findOsByClient(Long id) throws ClientNotFoundExeception{
        return osRepository.findAllByClient(id);
    }
    public List<Object> findAllByType(String typeOs) {
        TypeOs type = TypeOs.valueOf(typeOs.toUpperCase());
        return osRepository.findAllByType(type);
    }

    public List<Object> findAllByEquipment(String typeEquipment) {
        TypeEquipment type = TypeEquipment.valueOf(typeEquipment.toUpperCase());
        return osRepository.findAllByEquipment(type);
    }

    public List<Object> findByDateRange(String firstDate, String secondDate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate inicialDate = LocalDate.parse(firstDate, formatter);
        LocalDate finalDate = LocalDate.parse(secondDate, formatter);

        return osRepository.findAllByRegistrationDateBetween(inicialDate, finalDate);
    }
    @Transactional
    public OSDto save(OSDto osDto) throws ClientNotFoundExeception, TechnicianNotFoundExeception {

        var os = new OS();

        BeanUtils.copyProperties(osDto, os);


        os.setTypeOs(TypeOs.valueOf(osDto.getTypeOs().toUpperCase()));
        os.setTypeEquipment(TypeEquipment.valueOf(osDto.getTypeEquipment().toUpperCase()));

        os.setOwner(clientService.findClientById(osDto.getIdClient()));

        os.setTechnician(technicianService.findById(osDto.getIdTechnician()));

        os.setRegistrationDate(LocalDate.now());

        osRepository.save(os);

        return ResponseEntity.ok(osDto).getBody();
    }
    public void update(Long id, OSDto osDto) throws OsNotFoundExeception, ClientNotFoundExeception, TechnicianNotFoundExeception {

        Optional<OS> optionalOS = osRepository.findById(id);

        if (!optionalOS.isPresent()) {
            throw new OsNotFoundExeception("OS not found.");
        }

        String tipoEquipamento = osDto.getTypeEquipment().toUpperCase();
        String tipoOs = osDto.getTypeOs().toUpperCase();

        var os = new OS();

        BeanUtils.copyProperties(osDto, os);


        os.setTypeOs(TypeOs.valueOf(tipoOs));
        os.setTypeEquipment(TypeEquipment.valueOf(tipoEquipamento));


        os.setOwner(clientService.findClientById(osDto.getIdClient()));
        os.setTechnician(technicianService.findById(osDto.getIdTechnician()));


        //Para não alterar Id/RegistrationDate na atualização
        os.setRegistrationDate(optionalOS.get().getRegistrationDate());
        os.setId(optionalOS.get().getId());

        osRepository.save(os);
    }


    @Transactional
    public void delete(Long id) throws OsNotFoundExeception {
        Optional<OS> optionalOS = osRepository.findById(id);

        if (!optionalOS.isPresent()) {
            throw new OsNotFoundExeception("OS not found.");
        }
        osRepository.delete(optionalOS.get());
    }


}
