package com.mx.agenciamotos.repository;

import com.mx.agenciamotos.Model.detallesAgencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdetallesAgenciaDao extends JpaRepository<detallesAgencias, Integer> {


}
