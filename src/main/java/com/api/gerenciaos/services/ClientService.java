package com.api.gerenciaos.services;


import com.api.gerenciaos.domain.Client;
import com.api.gerenciaos.dto.client.ClientDto;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientAlreadyExistsExeception;
import com.api.gerenciaos.exceptions.ClientExceptions.ClientNotFoundExeception;
import com.api.gerenciaos.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public Page<Client> findAllClients(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public Client findClientById(@PathVariable Long id) throws ClientNotFoundExeception {
        return clientRepository.findById(id)
                .orElseThrow(()-> new ClientNotFoundExeception("Client not found"));
    }

    @Transactional
    public ClientDto saveClient(ClientDto clientDto) throws ClientAlreadyExistsExeception {
        Optional<Client> clientOptional  = clientRepository.findByEmail(clientDto.getEmailAddress());

        if(clientOptional.isPresent()){
            throw new ClientAlreadyExistsExeception("There is already a client with this e-mail registered in the system!");
        }

        //usar o tipo VAR é um novo recurso do java, ele "identifica" que o tipo da variável vai ser do tipo OS
        Client client = new Client();

        BeanUtils.copyProperties(clientDto, client);

        client.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        clientRepository.save(client);

        return ResponseEntity.ok(clientDto).getBody();

    }

    public void updateClient (Long id, ClientDto clientDto ) throws ClientNotFoundExeception, ClientAlreadyExistsExeception {

        Optional<Client> optionalClient = clientRepository.findById(id);

        if(!optionalClient.isPresent()){
            throw new ClientNotFoundExeception("Client not found!");
        }

        var client = new Client();

        if(!optionalClient.get().getEmailAddress().toLowerCase().equals(
                clientDto.getEmailAddress().toLowerCase())){
            throw new ClientAlreadyExistsExeception("Client already exists in the system!");
        }

        BeanUtils.copyProperties(clientDto, client);

        //Para não alterar Id/RegistrationDate na atualização
        client.setRegistrationDate(optionalClient.get().getRegistrationDate());
        client.setId(optionalClient.get().getId());

        clientRepository.save(client);

    }

    @Transactional
    public void delete(Long id) throws ClientNotFoundExeception{

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()){
            throw new ClientNotFoundExeception("Client not found");
        }

        clientRepository.delete(clientOptional.get());

    }
}
