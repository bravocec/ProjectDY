/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.security;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import turing.solutions.dy.business.registro.usuarios.UsuariosService;
import turing.solutions.dy.persistence.model.Usuarios;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import turing.solutions.dy.persistence.model.Roles;

/**
 *
 * @author Alan
 */
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
    
    private Logger log = Logger.getLogger(CustomUserDetailService.class);

    @Autowired
    private UsuariosService usuariosService;
    
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuarios usuario = this.usuariosService.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("El usuairo " + correo + " no existe, favor de verificar");
        }    
        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getActivo(), true, true, true, getGrantedAuthorities(usuario));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Usuarios usuario) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        StringBuilder sb = new StringBuilder();
        
        for (Roles rol : usuario.getRolesList()) {
            sb.append("ROLE_" + rol.getDescRol().toUpperCase()+",");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getDescRol().toUpperCase()));
        }
        log.info("Roles del usuario "+usuario.getEmail()+": "+sb.toString());
        return authorities;
    }

}
