package com.mx.agenciamotos.service;

import com.mx.agenciamotos.model.detallesAgencias;
import com.mx.agenciamotos.repository.IdetallesAgenciaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdetallesAgenciaServiceImp implements IdetallesAgenciaService{
    @Autowired
    private IdetallesAgenciaDao dao;

    @Override
    public List<detallesAgencias> list() {
        return dao.findAll();
    }

}