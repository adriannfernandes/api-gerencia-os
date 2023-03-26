package com.api.gerenciaos.domain;

import com.api.gerenciaos.domain.enums.TypeEquipment;
import com.api.gerenciaos.domain.enums.TypeOs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ORDER_SERVICE")
public class OS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOs typeOs;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeEquipment typeEquipment;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate registrationDate;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    @JsonIgnoreProperties({"history"})
    private Client owner;

    @JoinColumn(name = "technician_id")
    @ManyToOne
    @JsonIgnoreProperties({"historyOs"})
    private Technician technician;

    @Column(nullable = false, length = 70)
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOs getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(TypeOs typeOs) {
        this.typeOs = typeOs;
    }

    public TypeEquipment getTypeEquipment() {
        return typeEquipment;
    }

    public void setTypeEquipment(TypeEquipment typeEquipment) {
        this.typeEquipment = typeEquipment;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
}
