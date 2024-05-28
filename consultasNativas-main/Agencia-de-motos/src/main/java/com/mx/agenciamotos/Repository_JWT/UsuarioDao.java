package com.mx.agenciamotos.Repository_JWT;

import com.mx.agenciamotos.model_rol_usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

}