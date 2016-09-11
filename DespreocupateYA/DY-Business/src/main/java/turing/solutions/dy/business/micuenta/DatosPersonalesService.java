/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.micuenta;

import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
public interface DatosPersonalesService {
    
    Usuarios getDatosPersonales(Integer idUsuario);
    
    String actualizaDatos(String idUsuario,String nombres,String apellidos,String email,String telefono);
    
}
