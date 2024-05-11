package com.mx.agenciamotos.controller;

import com.mx.agenciamotos.Model.detallesAgencias;
import com.mx.agenciamotos.service.IdetallesAgenciaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Agencias")
@CrossOrigin
public class detallesAgenciasWS {
    @Autowired
    private IdetallesAgenciaServiceImp imp;

    @GetMapping("/list") // htpp://localhost:8090/api/Agencias/list
    public ResponseEntity<?> list(){
        List<detallesAgencias> agen = imp.list();
        return ResponseEntity.status(HttpStatus.OK).body(agen);


    }
  /*  @GetMapping("/todo")
    public ResponseEntity<?> todo(){
        List<detallesAgencias> ag = imp.getMotosConAgencias();
        return ResponseEntity.status(HttpStatus.OK).body(ag);
    }*/
}