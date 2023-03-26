package com.api.gerenciaos.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Technician extends Person implements Serializable {

    private final static long serialVersionUID = 1L;

    private String cpf;

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "technician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OS> historyOs = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<OS> getHistoryOs() {
        return historyOs;
    }

    public void setHistoryOs(List<OS> historyOs) {
        this.historyOs = historyOs;
    }
}
