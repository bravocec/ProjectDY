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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import turing.solutions.dy.business.proveedor.productos.ProductosService;
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
    
}
