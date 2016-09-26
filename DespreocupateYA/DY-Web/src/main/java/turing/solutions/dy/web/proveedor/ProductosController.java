/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.proveedor;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import turing.solutions.dy.business.proveedor.productos.ProductosService;
import turing.solutions.dy.persistence.model.ProductosServicios;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;

/**
 *
 * @author Alan
 */
@RestController
@RequestMapping("/proveedor/productos")
public class ProductosController {
    
    private static final Logger log = Logger.getLogger(ProductosController.class);
    private static final String STATUS = "status";
    
    @Autowired
    private ProductosService productosService;
    
    @RequestMapping(value="/list",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getProductos(){
        Map<String,Object> response = new HashMap<>();
        log.info("Listando productos");
        try{
            response.put("productos",this.productosService.getProdutosByProveedor());
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("Error al obtener los productos",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return response;
    }
    
    @RequestMapping(value="/persist",method = RequestMethod.POST,produces = "application/json")
    public Map<String,Object> guardaProducto(@RequestBody ProductosServicios prodcuto){
        Map<String,Object> response = new HashMap<>();
        log.info("Guardando el producto");
        log.info(prodcuto.toString());
        try{
            response.put("productId", this.productosService.guardaProducto(prodcuto));
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("No se pudo guardar el producto",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        
        return response;
    }
    
    @RequestMapping(value="/listSubcategorias",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getAllSubCategorias(){
        Map<String,Object> response = new HashMap<>();
        try{
            response.put("subCats", this.productosService.getAllSubCategorias());
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("No fue posible obtener las categorias",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        
        return response;
    }
    
    @RequestMapping(value="/listSucursalesByProveedor",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getAllSucursales(){
        Map<String,Object> response = new HashMap<>();
        try{
            response.put("sucs", this.productosService.getSucursalesByProveedor());
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("No fue posible obtener las categorias",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        
        return response;
    }
    
}
