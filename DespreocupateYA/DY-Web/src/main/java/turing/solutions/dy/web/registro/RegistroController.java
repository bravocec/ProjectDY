/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.registro;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import turing.solutions.dy.business.registro.usuarios.RegistroUsuariosService;
import turing.solutions.dy.persistence.model.Roles;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;
import turing.solutions.dy.util.enums.DYGeneralEnums;
import turing.solutions.dy.util.exceptions.DYRuntimeException;
import turing.solutions.dy.web.util.CommonProperties;

/**
 *
 * @author Alan
 */
@RestController
@RequestMapping("/registro")
public class RegistroController {

    private static final String STATUS = "status";
    private static final String DESCRIPCION = "descripcion";

    private final Logger log = Logger.getLogger(RegistroController.class);

    @Autowired
    private RegistroUsuariosService registroUsuariosService;

    //Este metodo es una prueba
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> registroUsuarios(Usuarios usuario) {
        Map<String, Object> map = new HashMap<>();
        if (usuario != null) {
            usuario.setActivo(true);
            List<Roles> roles = new ArrayList<Roles>();
            roles.add(new Roles(3, "Cliente"));
            roles.add(new Roles(1, "Cliente"));
            usuario.setRolesList(roles);
            usuario.setFechaCreacion(new Date());
        } else {
            map.put(STATUS, "-1");
            return map;
        }
        try {
            this.registroUsuariosService.creaUsuarios(usuario, null);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, "Error al crear el usuario: " + e.getMessage());
        }

        return map;
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> registraCliente(@RequestBody Usuarios usuario, @RequestParam(name = "telefono") String telefono) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            this.registroUsuariosService.creaUsuarios(usuario, telefono);
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        } catch (DYRuntimeException | NullPointerException e) {
            e.printStackTrace();
            if (e instanceof DYRuntimeException) {
                DYGeneralCodeMessages en = DYGeneralCodeMessages.getEnumFromString(e.getMessage());
                response.put(STATUS, en.getCodigo());
                response.put(DESCRIPCION, en.getDescripcion());
            } else {
                response.put(STATUS, 500);
            }
        }
        return response;
    }

    //TODO: Falta inclui categoria
    @RequestMapping(value = "/proveedores", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> registraProveedor(@RequestBody Usuarios proveedor,
            @RequestParam(name = "razon_social") String razonSocial,
            @RequestParam(name = "telefono") String telefono,
            @RequestParam(name = "telefono_movil") String telefonoMovil,
            @RequestParam(name = "direccion") String direccion,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "latlng") String latlng) {
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            log.info("Voy a persistir al Proveedor");
            Integer proveedorID = this.registroUsuariosService.creaProveedor(proveedor, razonSocial, telefono, telefonoMovil, direccion, Integer.MIN_VALUE, latlng);

            log.info("Proveedor Persistido con éxito");
            //System.out.println("Llego");
            //System.out.println(proveedor.toString());
            //System.out.println("razon_social: "+razonSocial);
            //System.out.println("telefono:"+telefono);
            //System.out.println("telefono_movil: "+telefonoMovil);
            //System.out.println("direccion: "+direccion);
            //System.out.println("Categoria: "+categoria);
            if (proveedorID == 0) {
                response.put(STATUS, 500);
            } else {
                response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
                response.put(DYGeneralCodeMessages.PROVEEDOR_ID.getCodigo(), proveedorID);
            }

        } catch (Exception e) {
            log.error("Hubo un error al presistir al proveedor " + proveedor.getRfc(), e);
            if (e instanceof DYRuntimeException) {
                DYGeneralCodeMessages en = DYGeneralCodeMessages.getEnumFromString(e.getMessage());
                response.put(STATUS, en.getCodigo());
                response.put(DESCRIPCION, en.getDescripcion());
            } else {
                response.put(STATUS, 500);
            }
        }
        return response;
    }

    /**
     * TODO: Pasar todo esto a la capa business.
     * @param files
     * @param idProveedor
     * @param nombreImagenSucursal
     * @param ife
     * @param comprobanteDomicilio
     * @param edoCuenta
     * @return 
     */
    @RequestMapping(value = "/documentos_proveedor", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> uploadDocumentos(@RequestParam(name = "file") MultipartFile[] files, @RequestParam(name = "idProveedor") Integer idProveedor,
            @RequestParam(name = "imagenSucursal") String nombreImagenSucursal,
            @RequestParam(name = "ife") String ife,
            @RequestParam(name = "comprobanteDomicilio") String comprobanteDomicilio,
            @RequestParam(name = "edoCuenta") String edoCuenta) {
        Map<String, Object> response = new HashMap<String, Object>();
        
        try {
            log.info("Tamaio del arreglo: " + files.length);
            if (idProveedor == null || idProveedor == 0) {
                throw new DYRuntimeException("Error en ID proveedor");
            }
            StringBuilder sb = new StringBuilder(CommonProperties.getResource("ruta.documentos.proveedor"));
            sb.append(idProveedor.toString());
            sb.append("/");
            log.info("Carpeta en donde se guardarán los archivos del  proveedor " + idProveedor.toString() + " será: " + sb.toString());

            File carpeta = new File(sb.toString());
            log.info("Verificando si existe la carpeta");
            if (!carpeta.exists()) {
                log.info("Creando la carpeta...");
                boolean creado = carpeta.mkdir();
                log.info(creado ? "Carpeta creada con éxito" : "Hubo un error al crear la carpeta");
                if (!creado) {
                    throw new DYRuntimeException("No se pudo crear la carpeta");
                }
            }
            
            Map<String,String> fileNames = fileNamestoHashMap(nombreImagenSucursal,ife,comprobanteDomicilio,edoCuenta);

            for (MultipartFile file : files) {
                StringBuilder sbFinal = new StringBuilder(sb);
                String nombreArchivo = fileNames.get(file.getOriginalFilename());
                sbFinal.append(nombreArchivo);
                if(new File(sbFinal.toString()).exists()){
                    throw new DYRuntimeException("El archivo "+sbFinal.toString()+" ya existe");
                }
                log.info("Nombre y ruta completa donde se guardará el archivo: " + sbFinal.toString());
                FileCopyUtils.copy(file.getBytes(), new FileOutputStream(new File(sbFinal.toString())));

            }
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        } catch (Exception e) {
            log.error("Error al dar de alta los documentos del proveedor", e);
            response.put(STATUS, 500);
        }

        return response;
    }

    private Map<String, String> fileNamestoHashMap(String... elements) {
        Map<String, String> map = new HashMap<>();
        map.put(elements[0], DYGeneralEnums.NOMBRE_IMAGEN_SUCURSAL.getValue());
        map.put(elements[1], DYGeneralEnums.IFE.getValue() + getExtencion(elements[1]));
        map.put(elements[2], DYGeneralEnums.COMPROBANTE_DOMICILIO.getValue() + getExtencion(elements[2]));
        map.put(elements[3], DYGeneralEnums.EDO_CUENTA.getValue() + getExtencion(elements[3]));
        return map;
    }
    
    private String getExtencion(String str){
        String[] ext = str.split("\\.");
        return "." + ext[ext.length-1];
    }

}
