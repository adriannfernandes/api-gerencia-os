package com.api.gerenciaos.dto.client;


import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1L;


    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
