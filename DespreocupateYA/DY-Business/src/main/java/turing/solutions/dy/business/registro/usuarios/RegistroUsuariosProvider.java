/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.registro.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.categorias.CategoriasServiciosDAO;
import turing.solutions.dy.persistence.dao.domicilios.DomiciliosDAO;
import turing.solutions.dy.persistence.dao.estatusproveedor.EstatusProveedorDAO;
import turing.solutions.dy.persistence.dao.roles.RolesDAO;
import turing.solutions.dy.persistence.dao.sucursales.SucursalesDAO;
import turing.solutions.dy.persistence.dao.telefonos.TipoTelefonoDAO;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.CategoriasServicios;
import turing.solutions.dy.persistence.model.Domicilios;
import turing.solutions.dy.persistence.model.EstatusProveedor;
import turing.solutions.dy.persistence.model.Proveedores;
import turing.solutions.dy.persistence.model.Roles;
import turing.solutions.dy.persistence.model.Sucursales;
import turing.solutions.dy.persistence.model.Telefonos;
import turing.solutions.dy.persistence.model.TipoTelefono;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;
import turing.solutions.dy.util.enums.DYGeneralEnums;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class RegistroUsuariosProvider implements RegistroUsuariosService {

    @Autowired
    private UsuariosDAO<Usuarios> usuariosDAO;

    @Autowired
    private RolesDAO<Roles> rolesDAO;

    @Autowired
    private CategoriasServiciosDAO<CategoriasServicios> categoriasServiciosDAO;

    @Autowired
    private TipoTelefonoDAO<TipoTelefono> tipoTelefonoDAO;
    
    @Autowired
    private DomiciliosDAO<Domicilios> domiciliosDAO;
    
    @Autowired
    private SucursalesDAO<Sucursales> sucursalesDAO;
    
    @Autowired
    private EstatusProveedorDAO<EstatusProveedor> estatusProveedorDAO;

    private Logger log = Logger.getLogger(RegistroUsuariosProvider.class);

    @Override
    @Transactional
    public void creaUsuarios(Usuarios usuario,String telefono) {
        if (usuario == null) {
            throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_RC_EMPTY.toString());
        } else {

            Usuarios usuarioTemp = this.usuariosDAO.findByCorreo(usuario.getEmail().trim());
            if (usuarioTemp != null) {
                throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_RC_EXISTE_USUARIO.toString());
            }

            List<Roles> roles = null;

            try {
                roles = this.rolesDAO.findByDescRol(DYGeneralEnums.ROL_CLIENTE.getValue());

                usuario.setRolesList(roles);
                usuario.setActivo(true);
                usuario.setFechaCreacion(new Date());
                
                //Telefonos
                
                List<Telefonos> telefonos = new ArrayList<Telefonos>();
                Telefonos tel = new Telefonos();
                tel.setTipoTelefonoIdTipotelefono(this.tipoTelefonoDAO.findByDescripcion("Movil"));
                tel.setPrincipal(true);
                tel.setTelefono(telefono);
                tel.setUsuariosIdUsuario(usuario);
                telefonos.add(tel);
                usuario.setTelefonosList(telefonos);
                
                this.usuariosDAO.save(usuario);
                log.info("Usuario Creado con exito");
            } catch (Exception e) {
                throw new RuntimeException(DYGeneralCodeMessages.ERROR_GENERAL.toString() + ": " + e.getMessage(), e);
            }
        }

    }

    //TODO: Validar los datos de entrada
    @Override
    @Transactional
    public int creaProveedor(Usuarios usuario, String razonSocial, String telefono, String telefonoMovil, String direccion, Integer categoria, String latlng) {
        int proveedorId = 0;
        if (usuario == null) {
            throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_RP_EMPTY.toString());
        } else {
            Usuarios user = this.usuariosDAO.findByRFC(usuario.getRfc());
            if (user != null) {
                throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_RP_RFC_DUPLICADO.toString() + usuario.getRfc());
            }
            user = this.usuariosDAO.findByCorreo(usuario.getEmail().trim());
            if (user != null) {
                throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_RP_EXISTE_PROVEEDOR.toString());
            }
            List<Roles> roles = null;
            try {
                roles = this.rolesDAO.findByDescRol(DYGeneralEnums.ROL_CLIENTE.getValue(), DYGeneralEnums.ROL_PROVEEDOR.getValue());;

                Proveedores proveedor = new Proveedores();
                proveedor.setRazonSocial(razonSocial);
                //TODO: Cambiar la categoria
                proveedor.setCategoriasServiciosIdCategoria(this.categoriasServiciosDAO.findCategoriaByDescripcion(DYGeneralEnums.CAT_HOGAR.getValue()));
                log.info("Categoria Seleccionada " + proveedor.getCategoriasServiciosIdCategoria().getDescripcion());
                //Los proveedores nacen como nuevos
                EstatusProveedor estatus = this.estatusProveedorDAO.findEstatusByDescripcion("Nuevo");
                proveedor.setEstatusProveedorId(estatus);

                
                usuario.setProveedoresIdProveedor(proveedor);
                usuario.setRolesList(roles);
                usuario.setActivo(true);
                usuario.setFechaCreacion(new Date());

                //Telefonos
                List<Telefonos> telefonos = new ArrayList<Telefonos>();
                Telefonos tel = new Telefonos();
                tel.setTipoTelefonoIdTipotelefono(this.tipoTelefonoDAO.findByDescripcion("Movil"));
                tel.setPrincipal(true);
                tel.setTelefono(telefonoMovil);
                tel.setUsuariosIdUsuario(usuario);
                telefonos.add(tel);

                tel = new Telefonos();
                tel.setTipoTelefonoIdTipotelefono(this.tipoTelefonoDAO.findByDescripcion("Fijo"));
                tel.setPrincipal(false);
                tel.setTelefono(telefono);
                tel.setUsuariosIdUsuario(usuario);
                telefonos.add(tel);

                usuario.setTelefonosList(telefonos);

                this.usuariosDAO.save(usuario);
                log.info("Proveedor creado con exito");
                
                //Domicilio
                Domicilios domicilio = armaDomicilio(direccion);
                
                String[] latitudLongitud = latlng.split(",");
                domicilio.setLatitud(Double.parseDouble(latitudLongitud[0]));
                domicilio.setLongitud(Double.parseDouble(latitudLongitud[1]));
                log.info("Usuario ID: "+usuario.getIdUsuario());
                proveedorId  = usuario.getProveedoresIdProveedor().getIdProveedor();
                log.info("Proveedor ID: "+proveedorId);
                domicilio.setUsuariosIdUsuario(usuario);
                domicilio.setPrincipal("Principal");
                log.info("Se procede a crear el domicilio");
                this.domiciliosDAO.save(domicilio);
                log.info("Domicilio creado con éxito");
                log.info("Domicilio ID: "+domicilio.getIdDomicilio());
                
                
                Sucursales sucursalDefault = new Sucursales();
                sucursalDefault.setNombreSucursal(razonSocial);
                sucursalDefault.setDomiciliosIdDomicilio(domicilio);                
                sucursalDefault.setProveedoresIdProveedor(proveedor);
                log.info("Se procede a crear la sucursal");
                this.sucursalesDAO.save(sucursalDefault);
                log.info("Sucursal creada con éxito");
                
                
            } catch (Exception e) {
                throw new RuntimeException(DYGeneralCodeMessages.ERROR_GENERAL.toString() + ": " + e.getMessage(), e);
            }

        }
        return proveedorId;
    }

    private Domicilios armaDomicilio(String direccion) {
        String[] domicilioArray = direccion.split(",");
        Domicilios dom = null;

        dom = new Domicilios();
        dom.setCalle(domicilioArray[0]);
        dom.setNumExt(domicilioArray[1]);
        dom.setNumInt(domicilioArray[2]);
        dom.setColonia(domicilioArray[3]);
        dom.setDelegacionMunicipio(domicilioArray[4]);
        dom.setEstado(domicilioArray[5]);
        dom.setCp(domicilioArray[6]);
        
        if(domicilioArray.length == 8){
            dom.setEntreCalles(domicilioArray[7]);
        }
        
        dom.setDescripcion("Dirección primaria: "+direccion);
        log.info("CP: "+dom.getCp());

        return dom;
    }

}
