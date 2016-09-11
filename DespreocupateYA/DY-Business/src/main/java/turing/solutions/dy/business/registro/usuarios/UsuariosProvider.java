/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.registro.usuarios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
@Service
public class UsuariosProvider implements UsuariosService {

    @Autowired
    private UsuariosDAO<Usuarios> usuariosDAO;

    @Override
    public Usuarios findByCorreo(String correo) {
        Usuarios usuario = null;
        try {
            usuario = usuariosDAO.findByCorreo(correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> getAllUsers() {
        return this.usuariosDAO.findAll(Usuarios.class);
    }

}
