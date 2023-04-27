package com.example.demo_alineacion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@PrimaryKeyJoinColumn(name = "identificacion")
@Table(name = "cliente")
public class Cliente extends Persona{
    private Long identificacion;
    private String contrasena;
    private String estado;
}
