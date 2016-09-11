/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.micuenta;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.domicilios.DomiciliosDAO;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.Domicilios;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.DYGeneralUtils;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class DireccionesClienteProvider implements DireccionesService {

    private Logger log = Logger.getLogger(DireccionesClienteProvider.class);

    @Autowired
    private DomiciliosDAO<Domicilios> daoDomicilios;

    @Autowired
    private UsuariosDAO<Usuarios> usuariosDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Domicilios> getDomicilios(String idUsuario) {
        if (!DYGeneralUtils.validaEntradas(idUsuario)) {
            throw new DYRuntimeException("El idUsuario no es valido");
        }
        log.info("----Obteniendo las direccionessssS");
        Usuarios usuario = this.usuariosDAO.findById(Integer.valueOf(idUsuario));
        log.info("Obteninendo los domicilios de "+usuario.getNombres());
        List<Domicilios> domiciliosUsuario = usuario.getDomiciliosList();
        
        for(Domicilios d : domiciliosUsuario){
            log.info("Calle "+d.getCalle());
        }
        
        return domiciliosUsuario != null && !domiciliosUsuario.isEmpty() ? domiciliosUsuario : null; // es esto
        
    }

}
