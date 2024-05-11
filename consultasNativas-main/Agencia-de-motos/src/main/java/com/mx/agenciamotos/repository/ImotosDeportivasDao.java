package com.mx.agenciamotos.repository;

import com.mx.agenciamotos.Model.motosDeportivas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImotosDeportivasDao extends JpaRepository<motosDeportivas, String> {

        @Query(value = "SELECT * FROM motos_deportivas WHERE precio >= ?1 ", nativeQuery = true)
      public  List<motosDeportivas> findByPrecio(int precio);

        @Query(value="SELECT * FROM motos_deportivas WHERE marca = ?1", nativeQuery = true)
    public List<motosDeportivas>findbyMarcaIgnoringCaseContaining(String marca);
}
