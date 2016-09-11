/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Alan
 */
@Embeddable
public class ProductosPedidoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PEDIDOS_ID_PEDIDO")
    private int pedidosIdPedido;
    @Basic(optional = false)
    @Column(name = "PRODUCTOS_SERVICIOS_ID_PRODSERV")
    private int productosServiciosIdProdserv;

    public ProductosPedidoPK() {
    }

    public ProductosPedidoPK(int pedidosIdPedido, int productosServiciosIdProdserv) {
        this.pedidosIdPedido = pedidosIdPedido;
        this.productosServiciosIdProdserv = productosServiciosIdProdserv;
    }

    public int getPedidosIdPedido() {
        return pedidosIdPedido;
    }

    public void setPedidosIdPedido(int pedidosIdPedido) {
        this.pedidosIdPedido = pedidosIdPedido;
    }

    public int getProductosServiciosIdProdserv() {
        return productosServiciosIdProdserv;
    }

    public void setProductosServiciosIdProdserv(int productosServiciosIdProdserv) {
        this.productosServiciosIdProdserv = productosServiciosIdProdserv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedidosIdPedido;
        hash += (int) productosServiciosIdProdserv;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPedidoPK)) {
            return false;
        }
        ProductosPedidoPK other = (ProductosPedidoPK) object;
        if (this.pedidosIdPedido != other.pedidosIdPedido) {
            return false;
        }
        if (this.productosServiciosIdProdserv != other.productosServiciosIdProdserv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.ProductosPedidoPK[ pedidosIdPedido=" + pedidosIdPedido + ", productosServiciosIdProdserv=" + productosServiciosIdProdserv + " ]";
    }
    
}
