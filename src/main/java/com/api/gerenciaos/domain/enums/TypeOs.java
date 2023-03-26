package com.api.gerenciaos.domain.enums;

public enum TypeOs {

    INSTALACAO("INSTALAÇÃO"),
    MANUTENÇÃO("MANUTENÇÃO"),
    SUPORTE("SUPORTE");

    private String description;

    TypeOs(String description){
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
