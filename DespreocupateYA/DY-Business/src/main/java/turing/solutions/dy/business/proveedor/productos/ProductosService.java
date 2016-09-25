/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.proveedor.productos;

import java.util.List;
import turing.solutions.dy.persistence.model.ProductosServicios;

/**
 *
 * @author Alan
 */
public interface ProductosService {
    
    List<ProductosServicios> getProdutosByProveedor();
    
}
