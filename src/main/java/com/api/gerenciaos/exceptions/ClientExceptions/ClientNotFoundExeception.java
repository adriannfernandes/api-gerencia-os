package com.api.gerenciaos.exceptions.ClientExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundExeception extends Exception {
        public ClientNotFoundExeception(String message) {
            super(message);
    }
}
