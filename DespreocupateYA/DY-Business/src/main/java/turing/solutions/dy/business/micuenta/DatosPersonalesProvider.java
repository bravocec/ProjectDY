/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.micuenta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.Telefonos;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class DatosPersonalesProvider implements DatosPersonalesService{

    @Autowired
    private UsuariosDAO<Usuarios> usuriosDAO;
    
    @Override
    @Transactional(readOnly = true)
    public Usuarios getDatosPersonales(Integer idUsuario) {
        if(idUsuario == null || idUsuario == 0){
            throw new DYRuntimeException("El id usuario viene nulo");
        }
        Usuarios usuario = this.usuriosDAO.findById(idUsuario);
        
        return usuario;
    }
    
    @Override
    @Transactional
    public String actualizaDatos(String idUsuario,String nombres,String apellidos,String email,String telefono){
        String estatus = null;
        if(idUsuario == null || idUsuario.isEmpty()){
            throw new DYRuntimeException("El id del usuario está nulo/no existe");
        }
        
        Usuarios usuario = this.usuriosDAO.findById(Integer.valueOf(idUsuario));
        
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        
        //usuario.getTelefonosList();
        
        List<Telefonos> listaTelefonos  = usuario.getTelefonosList();
        
        if(listaTelefonos != null && !listaTelefonos.isEmpty()){
            for(Telefonos tel : listaTelefonos){
                if(tel.getTipoTelefonoIdTipotelefono().getDescripcion().equals("Movil")){
                    tel.setTelefono(telefono);
                    break;
                }
            }
            this.usuriosDAO.update(usuario);
        }
        
        
        return estatus;
    }
    
    
}