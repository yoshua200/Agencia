package com.mx.agenciamotos.impl_JWT;

import com.mx.agenciamotos.Repository_JWT.UsuarioDao;
import com.mx.agenciamotos.model_rol_usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    //Inyectamos el repo
    @Autowired
    private UsuarioDao usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByUsername(username);

        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("El usuario: %s no existe dentro del sistema", username));
        }
        Usuario result = usuarioOptional.orElseThrow();
        List<GrantedAuthority> authorities = result.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());


        /*Usamor la clase User del spring security*/
        return new User(
                result.getUsername(),
                result.getPsw(),
                result.isHabilitado(),
                true,
                true,
                true,
                authorities
        );
    }
}

