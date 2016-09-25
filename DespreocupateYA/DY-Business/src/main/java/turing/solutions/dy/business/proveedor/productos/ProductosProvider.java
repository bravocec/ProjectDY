/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.proveedor.productos;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.business.common.CommonService;
import turing.solutions.dy.persistence.model.ProductosServicios;
import turing.solutions.dy.persistence.model.Sucursales;
import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
@Service
public class ProductosProvider implements ProductosService{
    
    @Autowired
    private CommonService commonService;
    
    private static final Logger log = Logger.getLogger(ProductosProvider.class);
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductosServicios> getProdutosByProveedor(){
        List<ProductosServicios> productos =  new ArrayList<>();;
        Usuarios usuario = commonService.currentUser();
        log.info("Id del usuario "+usuario.getIdUsuario());
        List<Sucursales> sucursales = usuario.getProveedoresIdProveedor().getSucursalesList();
        
        if(sucursales != null && !sucursales.isEmpty()){
            for(Sucursales s : sucursales){
                if(!s.getProductosServiciosList().isEmpty()){
                    productos.addAll(s.getProductosServiciosList());
                }
            }
        }else{
            log.info("Sin sucursales");
        }
        
        return productos;
    }
    
}
