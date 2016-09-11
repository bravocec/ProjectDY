/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.administrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import turing.solutions.dy.business.administrador.AdministradorService;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;
import turing.solutions.dy.web.util.CommonProperties;

/**
 *
 * @author Alan
 */
@Controller
@RequestMapping("/admin")
public class AdministradorController {

    private static final String STATUS = "status";
    private static final String PROVEEDORES = "proveedores";
    private final Logger log = Logger.getLogger(AdministradorController.class);

    @Autowired
    private AdministradorService administradorService;
    

    @RequestMapping(value = "/obtenerProveedores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getAllProveedores() {
        Map<String, Object> map = new HashMap<String, Object>();
        
        

        try {

            Set<Map<String, Object>> listProveedores = this.administradorService.findAll();
            map.put(PROVEEDORES, listProveedores);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());

        } catch (Exception e) {
            log.error("Hubo un error al obtener los proveedores", e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }

        return map;
    }

    @RequestMapping(value = "/detalleProveedor", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getDetalleProveedor(@RequestParam(name = "idProveedor") String idProveedor) {
        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, Object> datosProveedor = this.administradorService.getDetalleProveedorById(Integer.valueOf(idProveedor));
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
            map.put("proveedor", datosProveedor);
        } catch (Exception e) {
            log.error("Hubo un excecpcion al momento de obtener los datos del provedor", e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }

        return map;
    }

    @RequestMapping(value = "/resumenProveedores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getResumenProveedores() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("resumen", this.administradorService.getResumenProveedores());
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        } catch (Exception e) {
            log.info("Hubo un error al obtener el resumen de los proveedores", e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo() + ":" + e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/detalleDocs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getDetalleDocumentos(@RequestParam(name = "idProveedor") String idProveedor) {
        Map<String, Object> map = new HashMap();

        try {
            Map<String, Object> docs = this.administradorService.getDocumentosProveedor(Integer.valueOf(idProveedor), CommonProperties.getResource("ruta.documentos.proveedor"));
            map.put("documentos", docs.isEmpty() ? 0 : docs);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        } catch (Exception e) {
            log.info("Hubo un error al obtener el resumen de los proveedores", e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo() + ":" + e.getMessage());
        }

        return map;
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFileFromIdProveedor(@RequestParam(value = "idProveedor") String idProveedor, @RequestParam(value = "nombreArchivo") String nombreArchivo) {
        byte[] bytes = null;

        try {
            File archivo = new File(CommonProperties.getResource("ruta.documentos.proveedor") + idProveedor + "/" + nombreArchivo);
            if (archivo.exists()) {
                bytes = new byte[Long.valueOf(archivo.length()).intValue()];
                InputStream stream = new FileInputStream(archivo);
                stream.read(bytes);
                IOUtils.closeQuietly(stream);
                
                log.info("Archivo leido con Ã©xito: " + nombreArchivo);

            }
        } catch (Exception e) {
            log.error("Hubo un error al momento de obtener el archivo " + nombreArchivo + " del proveedor " + idProveedor, e);
        }

        return obtenerRespuestaHttpDescargaArchivo(nombreArchivo, bytes);
    }
    
    @RequestMapping(value="/documentosproveedor",method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String,Object> guardaDocumentoProveedor(@RequestParam(name = "file") MultipartFile file,
            @RequestParam(name="nombreArchivo")String nombreArchivo,
            @RequestParam(name="idProveedor") String idProveedor){
        Map<String,Object> map = new HashMap<>();
        log.info("Iniciando el guardado de documentos");
        
        try{
            StringBuilder sb = new StringBuilder(CommonProperties.getResource("ruta.documentos.proveedor"));
            sb.append(idProveedor);
            sb.append("/");
            this.administradorService.guardaArchivoProveedor(nombreArchivo, file, sb.toString());
        }catch(Exception e){
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo() + ":" + e.getMessage());
        }
        
        return map;
    }

    private ResponseEntity<byte[]> obtenerRespuestaHttpDescargaArchivo(String name, byte[] file) {
        String[] partesNombreArchivo = name.split(Pattern.quote("."));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(obtenerMediaType(partesNombreArchivo[partesNombreArchivo.length - 1]));
        headers.set("Content-Disposition",
                "attachment; filename=" + name.replace(" ", "_"));
        headers.setContentLength(file.length);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    private MediaType obtenerMediaType(String extension) {
        MediaType mediaType;

        switch (extension) {
            case "pdf":
                mediaType = new MediaType("application", "pdf");
                break;
            case "csv":
                mediaType = new MediaType("text", "csv");
                break;
            default:
                mediaType = new MediaType("text", "csv");
                break;
        }

        return mediaType;
    }

}
