package com.api.gerenciaos.domain.enums;

public enum TypeEquipment {

    NOTEBOOK("Notebook"),
    COMPUTADOR("Computador"),
    IMPRESSORA("Impressora");

    private String description;

    TypeEquipment(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
