package com.mx.agenciamotos.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="AGENCIA_DEPO")
@Data
@Getter
@Setter
public class detallesAgencias  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_AGENCIA")
    private Long idAgencia;

    @Column(name="NOMBRE_AGENCIA")
    private String nombreAgencia;

    @Column(name="DIRECCION")
    private String direccion;

    @Column(name="TELEFONO")
    private String telefono;

  //  @OneToMany(targetEntity = motosDeportivas.class)
  //  private List<motosDeportivas> motos;


// cada agencia puede tener muchas motos
//@JsonIgnore
/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
@JoinTable(name = "agencias_motos",
        joinColumns = @JoinColumn(name = "agencia_id", referencedColumnName = "ID_AGENCIA"),
        inverseJoinColumns = @JoinColumn(name = "moto_id", referencedColumnName = "N_SERIE"))
private List<motosDeportivas> motos = new ArrayList<>(); */

}