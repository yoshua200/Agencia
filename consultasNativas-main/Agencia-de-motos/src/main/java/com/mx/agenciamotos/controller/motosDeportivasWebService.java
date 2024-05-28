package com.mx.agenciamotos.controller;

import com.mx.agenciamotos.model.motosDeportivas;
import com.mx.agenciamotos.service.motosDeportivasServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/Deportivas")
@CrossOrigin
public class motosDeportivasWebService {

    @Autowired
     public motosDeportivasServiceImp ser;


    @GetMapping("/list") // http://localhost:8090/api/Deportivas/list
    public ResponseEntity<?> list() {
        List<motosDeportivas> motos = ser.list();
        if (motos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron resultados");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(motos);
        }
    }

    @GetMapping("/listPag")
    public ResponseEntity<Object> paginado(
            @RequestParam ("marca") String marca,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam(defaultValue = "nSerie") String SortBy,
            @RequestParam(defaultValue = "ASC")String direccion){
        try{
            Sort sort = Sort.by(Sort.Direction.fromString(direccion), SortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<motosDeportivas> pag = ser.paginacion(marca, pageable);
            Map<String,Object> json = new HashMap<>();

            if(pag.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay resultados");
            }else {
                json.put("data",pag);
                json.put("status", HttpStatus.OK);
             //   return ResponseEntity.status(HttpStatus.OK).body(pag);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }
        }catch(IllegalArgumentException e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Argumento no válido: " + e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw e;
        }


/*
        Sort sort = Sort.by(Sort.Direction.fromString(direccion), SortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<motosDeportivas> pag = ser.paginacion(marca, pageable);

        if(pag.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay resultados");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(pag);
        }
        */

        }

    @PostMapping("/search") //http://localhost:8090/api/Deportivas/search
    public ResponseEntity<?> search(@RequestBody motosDeportivas dep) {
        motosDeportivas deportivas = ser.search(dep);
        if (deportivas == null || deportivas.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro ninguna moto deportiva");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(deportivas);
        }

    }

    @PostMapping("/add") // http://localhost:8090/api/Deportivas/add
    public ResponseEntity<?> add(@RequestBody motosDeportivas dep) {
        motosDeportivas deportivas = ser.search(dep);
        if (deportivas == null || deportivas.equals(null)) {
            ser.add(dep);
            return ResponseEntity.status(HttpStatus.OK).body("La moto: " + dep.getMarca() + ", " + dep.getModelo() + " se guardo con exito");

        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body("El numero de serie de la moto ya existe, intenta con otro");
        }
    }

    @PutMapping("/edit") // http://localhost:8090/api/Deportivas/edit
    public ResponseEntity<?> edit(@RequestBody motosDeportivas dep) {
        if (ser.search(dep) != null) {
            ser.edit(dep);
            return ResponseEntity.status(HttpStatus.FOUND).body("Se edito con exito la moto, " + dep.getMarca());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El numero de serie de la moto es incorrecto o no existe");
        }
    }

    @DeleteMapping("/delete") //http://localhost:8090/api/Deportivas/delete
    public ResponseEntity<?> delete(@RequestBody motosDeportivas dep) {
        if (ser.search(dep) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La moto que intentas eliminar no existe o el numero de serie es incorrecto");

        } else {
            ser.delete(dep);
            return ResponseEntity.status(HttpStatus.OK).body("La moto se ha eliminado con exito");
        }
    }

    @GetMapping("/listPrecio") // http://localhost:8090/api/Deportivas/listPrecio
    public ResponseEntity<?> buscarPrecio(@RequestParam("precio") int precio) {
        List<motosDeportivas> motos = ser.buscarPrecio(precio);
        motosDeportivas deportivas = new motosDeportivas();

        if (motos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aun no tenemos motos mayores a " + "$" + precio);
        } else if (precio < 250000) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nuestro rango de precios en nuestra agencia es mayor o igual a $250,000");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(motos);
        }
    }

    @GetMapping("/marca")
    public ResponseEntity<?> marca(@RequestParam("marca") String marca){
        List<motosDeportivas> motos = ser.buscarMarca(marca);
        return ResponseEntity.status(HttpStatus.OK).body(motos);
    }
}








