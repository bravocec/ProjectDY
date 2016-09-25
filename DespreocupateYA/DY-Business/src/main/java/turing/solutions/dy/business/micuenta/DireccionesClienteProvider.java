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
import turing.solutions.dy.business.common.CommonService;
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
    private CommonService commonService;

    @Override
    @Transactional(readOnly = true)
    public List<Domicilios> getDomicilios() {
        Usuarios usuario = this.commonService.currentUser();
        log.info("Obteninendo los domicilios de " + usuario.getNombres());
        List<Domicilios> domiciliosUsuario = usuario.getDomiciliosList();
        return domiciliosUsuario != null && !domiciliosUsuario.isEmpty() ? domiciliosUsuario : null;
    }

    @Override
    @Transactional
    public void guardaDireccion(Domicilios domicilio) {
        domicilio.setUsuariosIdUsuario(this.commonService.currentUser());
        this.daoDomicilios.save(domicilio);
    }

    @Override
    @Transactional
    public void borraDomicilio(Domicilios domicilio) {
        this.daoDomicilios.delete(this.daoDomicilios.findById(domicilio.getIdDomicilio()));
    }
    
    @Override
    @Transactional
    public void actualizaDomicilio(Domicilios domicilio){
        domicilio.setUsuariosIdUsuario(this.commonService.currentUser());
        this.daoDomicilios.update(domicilio);
    }

    @Override
    @Transactional(readOnly = true)
    public Domicilios getDomicilioById(Integer idDomicilio) {
        return this.daoDomicilios.findById(idDomicilio);
    }

}
