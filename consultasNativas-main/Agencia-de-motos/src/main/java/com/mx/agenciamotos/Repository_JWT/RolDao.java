package com.mx.agenciamotos.Repository_JWT;

import com.mx.agenciamotos.model_rol_usuario.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolDao extends CrudRepository<Rol, Long> {

    /*Cuando se agrega un usuario se le asigna un rol ya existente
     * dentro de la BD, por eso hay que buscar un rol en la tabla*/

    Optional<Rol> findByNombre(String name);

}