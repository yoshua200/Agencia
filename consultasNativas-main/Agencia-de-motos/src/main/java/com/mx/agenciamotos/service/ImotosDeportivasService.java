package com.mx.agenciamotos.service;

import com.mx.agenciamotos.Model.motosDeportivas;

import java.util.List;

public interface ImotosDeportivasService {

    public void add(motosDeportivas dep);
    public void edit(motosDeportivas dep);
    public void delete (motosDeportivas dep);
    public motosDeportivas search(motosDeportivas dep);
    public List<motosDeportivas> list();
}
