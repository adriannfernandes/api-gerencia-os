package com.api.gerenciaos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TechnicianNotFoundExeception extends Exception{
    public TechnicianNotFoundExeception(String message) {
        super(message);
    }
}
