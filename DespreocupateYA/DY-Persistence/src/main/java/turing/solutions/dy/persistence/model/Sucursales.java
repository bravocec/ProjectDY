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
@Table(name = "sucursales", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursales.findAll", query = "SELECT s FROM Sucursales s"),
    @NamedQuery(name = "Sucursales.findByIdSucursal", query = "SELECT s FROM Sucursales s WHERE s.idSucursal = :idSucursal"),
    @NamedQuery(name = "Sucursales.findByNombreSucursal", query = "SELECT s FROM Sucursales s WHERE s.nombreSucursal = :nombreSucursal"),
    @NamedQuery(name = "Sucursales.findByDescripcion", query = "SELECT s FROM Sucursales s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sucursales.findByTelefonoUsuario", query = "SELECT s FROM Sucursales s WHERE s.telefonoUsuario = :telefonoUsuario"),
    @NamedQuery(name = "Sucursales.findByEntregaDomicilio", query = "SELECT s FROM Sucursales s WHERE s.entregaDomicilio = :entregaDomicilio")})
public class Sucursales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SUCURSAL")
    private Integer idSucursal;
    @Basic(optional = false)
    @Column(name = "NOMBRE_SUCURSAL")
    private String nombreSucursal;
    @Lob
    @Column(name = "IMAGEN",columnDefinition="blob")
    private byte[] imagen;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "TELEFONO_USUARIO")
    private Boolean telefonoUsuario;
    @Column(name = "ENTREGA_DOMICILIO")
    private Boolean entregaDomicilio;
    @OneToMany(mappedBy = "sucursalesIdSucursal", fetch = FetchType.LAZY)
    private List<Telefonos> telefonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalesIdSucursal", fetch = FetchType.LAZY)
    private List<ProductosServicios> productosServiciosList;
    @JoinColumn(name = "PROVEEDORES_ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedores proveedoresIdProveedor;
    @JoinColumn(name = "DOMICILIOS_ID_DOMICILIO", referencedColumnName = "ID_DOMICILIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Domicilios domiciliosIdDomicilio;

    public Sucursales() {
    }

    public Sucursales(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursales(Integer idSucursal, String nombreSucursal) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(Boolean telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Boolean getEntregaDomicilio() {
        return entregaDomicilio;
    }

    public void setEntregaDomicilio(Boolean entregaDomicilio) {
        this.entregaDomicilio = entregaDomicilio;
    }

    @XmlTransient
    public List<Telefonos> getTelefonosList() {
        return telefonosList;
    }

    public void setTelefonosList(List<Telefonos> telefonosList) {
        this.telefonosList = telefonosList;
    }

    @XmlTransient
    public List<ProductosServicios> getProductosServiciosList() {
        return productosServiciosList;
    }

    public void setProductosServiciosList(List<ProductosServicios> productosServiciosList) {
        this.productosServiciosList = productosServiciosList;
    }

    public Proveedores getProveedoresIdProveedor() {
        return proveedoresIdProveedor;
    }

    public void setProveedoresIdProveedor(Proveedores proveedoresIdProveedor) {
        this.proveedoresIdProveedor = proveedoresIdProveedor;
    }

    public Domicilios getDomiciliosIdDomicilio() {
        return domiciliosIdDomicilio;
    }

    public void setDomiciliosIdDomicilio(Domicilios domiciliosIdDomicilio) {
        this.domiciliosIdDomicilio = domiciliosIdDomicilio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursal != null ? idSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursales)) {
            return false;
        }
        Sucursales other = (Sucursales) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Sucursales[ idSucursal=" + idSucursal + " ]";
    }
    
}
