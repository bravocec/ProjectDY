/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "proveedores", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.findAllOrderById", query = "SELECT p FROM Proveedores p ORDER BY p.idProveedor ASC"),
    @NamedQuery(name = "Proveedores.findByIdProveedor", query = "SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedores.findByRazonSocial", query = "SELECT p FROM Proveedores p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "Proveedores.findByValoracionUsuarios", query = "SELECT p FROM Proveedores p WHERE p.valoracionUsuarios = :valoracionUsuarios")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "VALORACION_USUARIOS")
    private Integer valoracionUsuarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedoresIdProveedor", fetch = FetchType.LAZY)
    private List<Sucursales> sucursalesList;
    @JoinColumn(name = "CATEGORIAS_SERVICIOS_ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriasServicios categoriasServiciosIdCategoria;
    @JoinColumn(name = "ID_ESTATUSPROVEEDOR", referencedColumnName = "ID_ESTATUSPROVEEDOR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusProveedor estatusProveedorId;
    @OneToMany(mappedBy = "proveedoresIdProveedor", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedores(Integer idProveedor, String razonSocial) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
    }

    public EstatusProveedor getEstatusProveedorId() {
        return estatusProveedorId;
    }

    public void setEstatusProveedorId(EstatusProveedor estatusProveedorId) {
        this.estatusProveedorId = estatusProveedorId;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getValoracionUsuarios() {
        return valoracionUsuarios;
    }

    public void setValoracionUsuarios(Integer valoracionUsuarios) {
        this.valoracionUsuarios = valoracionUsuarios;
    }

    @XmlTransient
    public List<Sucursales> getSucursalesList() {
        return  sucursalesList;
    }

    public void setSucursalesList(List<Sucursales> sucursalesList) {
        this.sucursalesList = sucursalesList;
    }

    public CategoriasServicios getCategoriasServiciosIdCategoria() {
        return categoriasServiciosIdCategoria;
    }

    public void setCategoriasServiciosIdCategoria(CategoriasServicios categoriasServiciosIdCategoria) {
        this.categoriasServiciosIdCategoria = categoriasServiciosIdCategoria;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Proveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
