package com.api.gerenciaos.dto.technician;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class TechnicianDto implements Serializable {

    private final static long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
