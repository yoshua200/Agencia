package com.mx.agenciamotos.repository;

import com.mx.agenciamotos.model.detallesAgencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdetallesAgenciaDao extends JpaRepository<detallesAgencias, Integer> {


}
