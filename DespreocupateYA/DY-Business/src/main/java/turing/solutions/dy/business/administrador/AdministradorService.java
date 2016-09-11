/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.administrador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;
import turing.solutions.dy.persistence.model.Proveedores;

/**
 *
 * @author Alan
 */
public interface AdministradorService {
    
    Set<Map<String,Object>> findAll();
    
    Map<String, Object> getResumenProveedores();
    
    Map<String,Object> getDetalleProveedorById(Integer id);
    
    Map<String,Object> getDocumentosProveedor(Integer idProveedor,String ruta);
    
    void guardaArchivoProveedor( String nombreArchivo, MultipartFile file,String ruta) throws FileNotFoundException, IOException;
    
}
