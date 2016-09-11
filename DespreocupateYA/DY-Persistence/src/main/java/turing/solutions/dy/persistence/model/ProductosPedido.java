/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "productos_pedido", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosPedido.findAll", query = "SELECT p FROM ProductosPedido p"),
    @NamedQuery(name = "ProductosPedido.findByPedidosIdPedido", query = "SELECT p FROM ProductosPedido p WHERE p.productosPedidoPK.pedidosIdPedido = :pedidosIdPedido"),
    @NamedQuery(name = "ProductosPedido.findByProductosServiciosIdProdserv", query = "SELECT p FROM ProductosPedido p WHERE p.productosPedidoPK.productosServiciosIdProdserv = :productosServiciosIdProdserv"),
    @NamedQuery(name = "ProductosPedido.findByCantidad", query = "SELECT p FROM ProductosPedido p WHERE p.cantidad = :cantidad")})
public class ProductosPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosPedidoPK productosPedidoPK;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private int cantidad;
    @JoinColumn(name = "PRODUCTOS_SERVICIOS_ID_PRODSERV", referencedColumnName = "ID_PRODSERV", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductosServicios productosServicios;
    @JoinColumn(name = "PEDIDOS_ID_PEDIDO", referencedColumnName = "ID_PEDIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedidos pedidos;

    public ProductosPedido() {
    }

    public ProductosPedido(ProductosPedidoPK productosPedidoPK) {
        this.productosPedidoPK = productosPedidoPK;
    }

    public ProductosPedido(ProductosPedidoPK productosPedidoPK, int cantidad) {
        this.productosPedidoPK = productosPedidoPK;
        this.cantidad = cantidad;
    }

    public ProductosPedido(int pedidosIdPedido, int productosServiciosIdProdserv) {
        this.productosPedidoPK = new ProductosPedidoPK(pedidosIdPedido, productosServiciosIdProdserv);
    }

    public ProductosPedidoPK getProductosPedidoPK() {
        return productosPedidoPK;
    }

    public void setProductosPedidoPK(ProductosPedidoPK productosPedidoPK) {
        this.productosPedidoPK = productosPedidoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductosServicios getProductosServicios() {
        return productosServicios;
    }

    public void setProductosServicios(ProductosServicios productosServicios) {
        this.productosServicios = productosServicios;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosPedidoPK != null ? productosPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPedido)) {
            return false;
        }
        ProductosPedido other = (ProductosPedido) object;
        if ((this.productosPedidoPK == null && other.productosPedidoPK != null) || (this.productosPedidoPK != null && !this.productosPedidoPK.equals(other.productosPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.ProductosPedido[ productosPedidoPK=" + productosPedidoPK + " ]";
    }
    
}
