package com.mx.agenciamotos.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="AGENCIA_DEPO")
@Data
@Getter
@Setter
public class detallesAgencias  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_AGENCIA")
    private int idAgencia;

    @Column(name="NOMBRE_AGENCIA")
    private String nombreAgencia;

    @Column(name="DIRECCION")
    private String direccion;

    @Column(name="TELEFONO")
    private String telefono;

    }