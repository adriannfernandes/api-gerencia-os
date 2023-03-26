package com.api.gerenciaos.dto.os;

import com.api.gerenciaos.domain.enums.TypeEquipment;
import com.api.gerenciaos.domain.enums.TypeOs;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.io.Serializable;

public class OSDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 70)
    private String description;

    @NotNull
    private String typeOs;

    @NotNull
    private String typeEquipment;

    @NotNull
    private Long idClient;


    @NotNull
    private Long idTechnician;

    private Double price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOs() {
        return this.typeOs;
    }

    public void setTipoOs(String typeOs) {
        this.typeOs = typeOs;
    }

    public String getTypeEquipment() {
        return typeEquipment;
    }

    public void setTypeEquipment(String typeEquipment) {
        this.typeEquipment = typeEquipment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getIdClient() {
        return idClient;
    }
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public void setTypeOs(String typeOs) {
        this.typeOs = typeOs;
    }

    public Long getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(Long idTechnician) {
        this.idTechnician = idTechnician;
    }
}
