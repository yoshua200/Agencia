package com.mx.agenciamotos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "MOTOS")
@Data
public class motosDeportivas {

    @Id
   // @Column(name="ID_MOTO")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;

    @Column(name="N_SERIE")
    private String nSerie;

    @Column(name="C_C")
    private int cc;

    @Column(name="VELOCIDADMAX")
    private int velocidadmax;

    @Column(name="MARCA")
    private String marca;

    @Column(name="MODELO")
    private String modelo;

    @Column(name="ANIO")
    private int anio;

    @Column(name="COLOR")
    private String color;

    @Column (name="PRECIO")
    private int precio;

    @JoinColumn(name = "FK_IDAGENCIA", referencedColumnName = "ID_AGENCIA")
    @ManyToOne(optional = false)
    private detallesAgencias fkDetalles;

}
