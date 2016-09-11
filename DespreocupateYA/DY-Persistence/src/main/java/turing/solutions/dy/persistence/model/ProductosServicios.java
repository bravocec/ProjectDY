/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "productos_servicios", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosServicios.findAll", query = "SELECT p FROM ProductosServicios p"),
    @NamedQuery(name = "ProductosServicios.findByIdProdserv", query = "SELECT p FROM ProductosServicios p WHERE p.idProdserv = :idProdserv"),
    @NamedQuery(name = "ProductosServicios.findByTitulo", query = "SELECT p FROM ProductosServicios p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "ProductosServicios.findBySubTitulo", query = "SELECT p FROM ProductosServicios p WHERE p.subTitulo = :subTitulo"),
    @NamedQuery(name = "ProductosServicios.findByDescripcion", query = "SELECT p FROM ProductosServicios p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductosServicios.findByDisponible", query = "SELECT p FROM ProductosServicios p WHERE p.disponible = :disponible"),
    @NamedQuery(name = "ProductosServicios.findByCantidad", query = "SELECT p FROM ProductosServicios p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProductosServicios.findByPrecio", query = "SELECT p FROM ProductosServicios p WHERE p.precio = :precio"),
    @NamedQuery(name = "ProductosServicios.findByIsServicio", query = "SELECT p FROM ProductosServicios p WHERE p.isServicio = :isServicio"),
    @NamedQuery(name = "ProductosServicios.findByPorHora", query = "SELECT p FROM ProductosServicios p WHERE p.porHora = :porHora")})
public class ProductosServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODSERV")
    private Integer idProdserv;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "SUB_TITULO")
    private String subTitulo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "DISPONIBLE")
    private boolean disponible;
    @Lob
    @Column(name = "IMAGEN",columnDefinition="blob")
    private byte[] imagen;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "IS_SERVICIO")
    private Boolean isServicio;
    @Column(name = "POR_HORA")
    private Boolean porHora;
    @JoinColumn(name = "SUCURSALES_ID_SUCURSAL", referencedColumnName = "ID_SUCURSAL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursalesIdSucursal;
    @JoinColumn(name = "CAT_PROD_SERV_ID_CATPRODSERV", referencedColumnName = "ID_CATPRODSERV")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatProdServ catProdServIdCatprodserv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosServiciosIdProdserv", fetch = FetchType.LAZY)
    private List<Personal> personalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosServicios", fetch = FetchType.LAZY)
    private List<ProductosPedido> productosPedidoList;

    public ProductosServicios() {
    }

    public ProductosServicios(Integer idProdserv) {
        this.idProdserv = idProdserv;
    }

    public ProductosServicios(Integer idProdserv, String titulo, String descripcion, boolean disponible) {
        this.idProdserv = idProdserv;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public Integer getIdProdserv() {
        return idProdserv;
    }

    public void setIdProdserv(Integer idProdserv) {
        this.idProdserv = idProdserv;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getIsServicio() {
        return isServicio;
    }

    public void setIsServicio(Boolean isServicio) {
        this.isServicio = isServicio;
    }

    public Boolean getPorHora() {
        return porHora;
    }

    public void setPorHora(Boolean porHora) {
        this.porHora = porHora;
    }

    public Sucursales getSucursalesIdSucursal() {
        return sucursalesIdSucursal;
    }

    public void setSucursalesIdSucursal(Sucursales sucursalesIdSucursal) {
        this.sucursalesIdSucursal = sucursalesIdSucursal;
    }

    public CatProdServ getCatProdServIdCatprodserv() {
        return catProdServIdCatprodserv;
    }

    public void setCatProdServIdCatprodserv(CatProdServ catProdServIdCatprodserv) {
        this.catProdServIdCatprodserv = catProdServIdCatprodserv;
    }

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @XmlTransient
    public List<ProductosPedido> getProductosPedidoList() {
        return productosPedidoList;
    }

    public void setProductosPedidoList(List<ProductosPedido> productosPedidoList) {
        this.productosPedidoList = productosPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdserv != null ? idProdserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosServicios)) {
            return false;
        }
        ProductosServicios other = (ProductosServicios) object;
        if ((this.idProdserv == null && other.idProdserv != null) || (this.idProdserv != null && !this.idProdserv.equals(other.idProdserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.ProductosServicios[ idProdserv=" + idProdserv + " ]";
    }
    
}
