/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.registro.usuarios;

import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
public interface RegistroUsuariosService {

    void creaUsuarios(Usuarios usuario,String telefono);

    int creaProveedor(Usuarios usuario, String razonSocial,String telefono,String telefonoMovil,String direccion, Integer categoria,String latlng);

}
