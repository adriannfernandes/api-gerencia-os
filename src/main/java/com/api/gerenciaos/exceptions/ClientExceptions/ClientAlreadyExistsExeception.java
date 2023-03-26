package com.api.gerenciaos.exceptions.ClientExceptions;

public class ClientAlreadyExistsExeception extends Exception {

    public ClientAlreadyExistsExeception() {
        super();
    }

    public ClientAlreadyExistsExeception(String message) {
        super(message);
    }
}

