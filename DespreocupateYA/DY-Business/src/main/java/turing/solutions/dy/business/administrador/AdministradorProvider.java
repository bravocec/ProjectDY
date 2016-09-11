/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.administrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import turing.solutions.dy.persistence.dao.proveedores.ProveedoresDAO;
import turing.solutions.dy.persistence.model.Domicilios;
import turing.solutions.dy.persistence.model.Proveedores;
import turing.solutions.dy.persistence.model.Telefonos;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.Base64;
import turing.solutions.dy.util.DYGeneralUtils;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;
import turing.solutions.dy.util.enums.DYGeneralEnums;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class AdministradorProvider implements AdministradorService {

    @Autowired
    private ProveedoresDAO<Proveedores> proveedoresDAO;

    private Logger log = Logger.getLogger(AdministradorProvider.class);

    @Override
    @Transactional(readOnly = true)
    public Set<Map<String, Object>> findAll() {
        Set<Map<String, Object>> set = null;
        List<Proveedores> proveedores = this.proveedoresDAO.findAll(Proveedores.class);

        if (proveedores == null || proveedores.isEmpty()) {
            throw new DYRuntimeException("No hay proveedores disponibles");
        }
        set = new LinkedHashSet<Map<String, Object>>();
        Map<String, Object> map = null;

        for (Proveedores p : proveedores) {
            map = new HashMap<String, Object>();

            map.put("idProveedor", p.getIdProveedor());
            map.put("razonSocial", p.getRazonSocial());
            Usuarios usuario = p.getUsuariosList().get(0);
            map.put("rfc", usuario.getRfc());
            map.put("nombres", usuario.getNombres());
            map.put("correo", usuario.getEmail());
            map.put("telefono", getTelefonoPrincipal(usuario.getTelefonosList()));
            map.put("estatus", p.getEstatusProveedorId().getDescripcion());

            set.add(map);
        }

        return set;
    }

    private String getTelefonoPrincipal(List<Telefonos> telefonos) {
        String telefono = null;
        for (Telefonos tel : telefonos) {
            if (tel.getPrincipal()) {
                telefono = tel.getTelefono();
                break;
            }
        }
        return telefono;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getDetalleProveedorById(Integer id) {
        Proveedores p = this.proveedoresDAO.findById(id);
        if (p == null) {
            throw new DYRuntimeException(DYGeneralCodeMessages.ERROR_PARAMETROS_INSUFICIENTES.toString());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Usuarios u = p.getUsuariosList().get(0);
        List<Telefonos> telefonos = u.getTelefonosList();
        List<Domicilios> domicilios = u.getDomiciliosList();
        Domicilios domicilio = domicilios != null && !domicilios.isEmpty() ? domicilios.get(0) : null;
        map.put("nombres", u.getNombres());
        map.put("apellidos", u.getApellidos());
        map.put("email", u.getEmail());
        map.put("razon", p.getRazonSocial());
        map.put("rfc", u.getRfc());
        //TODO: Hay que cambiar esta parte para que sea capaz de soportar todos los telefonos
        if (telefonos != null) {
            for (Telefonos t : telefonos) {
                if (t.getTipoTelefonoIdTipotelefono().getDescripcion().equals("Fijo")
                        && map.get("telefono") == null) {
                    map.put("telefono", t.getTelefono());
                }
                if (t.getTipoTelefonoIdTipotelefono().getDescripcion().equals("Movil")
                        && map.get("telefonomovil") == null) {
                    map.put("telefonomovil", t.getTelefono());
                }
            }
        }

        //Llenando los domicilios
        llenaDomicilio(domicilio, map);

        return map;
    }

    private void llenaDomicilio(Domicilios domicilio, Map<String, Object> map) {
        if (domicilio != null) {
            map.put("calle", domicilio.getCalle());
            map.put("noexterior", domicilio.getNumExt());
            map.put("nointerior", domicilio.getNumInt());
            map.put("colonia", domicilio.getColonia());
            map.put("delegacion", domicilio.getDelegacionMunicipio());
            map.put("estado", domicilio.getEstado());
            map.put("cp", domicilio.getCp());
            if (DYGeneralUtils.validaEntradas(domicilio.getEntreCalles())) {
                String[] entreCalles = domicilio.getEntreCalles().split(",");
                map.put("entrecalle1", entreCalles[0]);
                map.put("entrecalle2", entreCalles.length > 1 ? entreCalles[1] : "");

            }
            map.put("domi", 1);
        } else {
            map.put("domi", 0);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getResumenProveedores() {
        List<Proveedores> proveedores = this.proveedoresDAO.findAll(Proveedores.class);
        //TODO: Formatear esto en el controller
        if (proveedores == null || proveedores.isEmpty()) {
            throw new DYRuntimeException("No hay proveedores disponibles");
        }
        Map<String, Object> map = new HashMap<String, Object>();

        Integer aceptados = 0, nuevos = 0, pendientes = 0, rechazados = 0;

        for (Proveedores p : proveedores) {
            if (DYGeneralEnums.ESTATUS_PROVEEDOR_NUEVO.getValue().equals(p.getEstatusProveedorId().getDescripcion())) {
                nuevos++;
            }
            if (DYGeneralEnums.ESTATUS_PROVEEDOR_ACEPTADO.getValue().equals(p.getEstatusProveedorId().getDescripcion())) {
                aceptados++;
            }
            if (DYGeneralEnums.ESTATUS_PROVEEDOR_PENDIENTE.getValue().equals(p.getEstatusProveedorId().getDescripcion())) {
                pendientes++;
            }
            if (DYGeneralEnums.ESTATUS_PROVEEDOR_RECHAZADO.getValue().equals(p.getEstatusProveedorId().getDescripcion())) {
                rechazados++;
            }
        }

        map.put(DYGeneralEnums.ESTATUS_PROVEEDOR_ACEPTADO.getValue().toLowerCase(), aceptados);
        map.put(DYGeneralEnums.ESTATUS_PROVEEDOR_NUEVO.getValue().toLowerCase(), nuevos);
        map.put(DYGeneralEnums.ESTATUS_PROVEEDOR_PENDIENTE.getValue().toLowerCase(), pendientes);
        map.put(DYGeneralEnums.ESTATUS_PROVEEDOR_RECHAZADO.getValue().toLowerCase(), rechazados);

        return map;
    }

    @Override
    public Map<String, Object> getDocumentosProveedor(Integer idProveedor, String ruta) {
        Map<String, Object> map = null;
        if (idProveedor == null || idProveedor <= 0) {
            throw new DYRuntimeException("ProveedorId Inválido");
        }
        File carpetaProveedor = new File(ruta + idProveedor);
        map = new HashMap();
        log.info("Intentando obtener los documentos del proveedor en la siguiente ruta "+carpetaProveedor.getAbsolutePath());
        if (carpetaProveedor.exists() && carpetaProveedor.isDirectory()) {
            File[] archivosProveedor = carpetaProveedor.listFiles();
            for (File f : archivosProveedor) {
                String name = f.getName();
                String[] pams = name.split("\\.");
                log.info("Nombre Archivo: " + name);
                if (name.matches(DYGeneralEnums.NOMBRE_IMAGEN_SUCURSAL.getValue())) {
                    try {
                        byte[] array = new byte[Long.valueOf(f.length()).intValue()];
                        new FileInputStream(f).read(array);
                        map.put("imagenSucursal", Base64.encodeToString(array, false));
                        map.put("extencion",".jpg");
                    } catch (IOException e) {
                        map.put("imagenSucursal", 0);
                    }
                } else {
                    DYGeneralEnums dyEnum = DYGeneralEnums.getEnumFromValue(pams[0]);
                    if(dyEnum != null){
                        map.put(dyEnum.getValue().toLowerCase(), name);
                    }
                    
                }
            }
        }
        return map;
    }
    
    @Override
    public void guardaArchivoProveedor( String nombreArchivo, MultipartFile file,String ruta) throws FileNotFoundException, IOException{
        if(!DYGeneralUtils.validaEntradas(nombreArchivo,ruta)){
            throw new DYRuntimeException("El idUsuario y el nombre de archivo es invalido");
        }
        File rutaProveedor = new File(ruta);
        
        if(!rutaProveedor.exists()){
            boolean carpeta = rutaProveedor.mkdir();
            if(!carpeta){
                throw new DYRuntimeException("No se pudo crear la carpeta "+ruta);
            }
        }
        rutaProveedor.setWritable(true);
        OutputStream out = null;
        final String pdf = ".pdf";
        switch(nombreArchivo){
            case "imagenSucursal":
                String archivoFinal = ruta+DYGeneralEnums.NOMBRE_IMAGEN_SUCURSAL.getValue();
                out = new FileOutputStream(new File(archivoFinal));
                log.info("Creando el archivo: "+archivoFinal);
                out.write(file.getBytes());
                IOUtils.closeQuietly(out);
                break;
            case "ife":
                out = new FileOutputStream(new File(ruta+DYGeneralEnums.IFE.getValue() + pdf));
                log.info("Creando el archivo: "+DYGeneralEnums.IFE.getValue());
                out.write(file.getBytes());
                IOUtils.closeQuietly(out);
                break;
            case "comrobanteDomicilio":
                out = new FileOutputStream(new File(ruta+DYGeneralEnums.COMPROBANTE_DOMICILIO.getValue() + pdf));
                log.info("Creando el archivo: "+DYGeneralEnums.COMPROBANTE_DOMICILIO.getValue());
                out.write(file.getBytes());
                IOUtils.closeQuietly(out);
                break;
            case "edoCuenta":
                out = new FileOutputStream(new File(ruta+DYGeneralEnums.EDO_CUENTA.getValue() + pdf));
                log.info("Creando el archivo: "+DYGeneralEnums.EDO_CUENTA.getValue());
                out.write(file.getBytes());
                IOUtils.closeQuietly(out);
                break;
        }
        
    }
   

}
