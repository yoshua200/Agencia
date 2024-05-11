package com.mx.agenciamotos.controller;

import com.mx.agenciamotos.Model.motosDeportivas;
import com.mx.agenciamotos.service.motosDeportivasServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Deportivas")
@CrossOrigin
public class motosDeportivasWebService {

    @Autowired
     public motosDeportivasServiceImp ser;
    //private IagenciaDeportivasDao dao;

    @GetMapping("/list") // http://localhost:8090/api/Deportivas/list
    public ResponseEntity<?> list() {
        List<motosDeportivas> motos = ser.list();
        if (motos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraros resultados");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(motos);
        }
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








