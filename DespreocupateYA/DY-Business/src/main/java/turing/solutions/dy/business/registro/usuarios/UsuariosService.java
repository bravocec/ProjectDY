/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.registro.usuarios;

import java.util.List;
import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
public interface UsuariosService {
    
    Usuarios findByCorreo(String correo);
    
    List<Usuarios> getAllUsers();
    
}
