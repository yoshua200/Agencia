package com.mx.agenciamotos.service;

import com.mx.agenciamotos.model.motosDeportivas;
import com.mx.agenciamotos.repository.ImotosDeportivasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class motosDeportivasServiceImp implements ImotosDeportivasService {
    @Autowired
    private ImotosDeportivasDao dao;

    @Override
    public void add(motosDeportivas dep) {

        dao.save(dep);
    }

    @Override
    public void edit(motosDeportivas dep) {
    dao.save(dep);
    }

    @Override
    public void delete(motosDeportivas dep) {
    dao.delete(dep);
    }

   @Override
    public motosDeportivas search(motosDeportivas dep) {
        return dao.findById(dep.getNSerie()).orElse(null);
    }

    @Override
    public List<motosDeportivas> list() {
        return dao.findAll();
    }

    public Page<motosDeportivas> paginacion( String marca, Pageable pageable){
        return dao.findAllMotosbyMarca(marca.toUpperCase(), pageable);
    }

 public List<motosDeportivas> buscarPrecio(int precio){
        return dao.findByPrecio(precio);
    }
    public List<motosDeportivas> buscarMarca(String marca){
        return dao.findbyMarcaIgnoringCaseContaining(marca.toUpperCase());
    }
}
