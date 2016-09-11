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
import javax.persistence.Lob;
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
@Table(name = "categorias_servicios", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasServicios.findAll", query = "SELECT c FROM CategoriasServicios c"),
    @NamedQuery(name = "CategoriasServicios.findByIdCategoria", query = "SELECT c FROM CategoriasServicios c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "CategoriasServicios.findByDescripcion", query = "SELECT c FROM CategoriasServicios c WHERE c.descripcion = :descripcion")})
public class CategoriasServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "RUTA")
    private String ruta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriasServiciosIdCategoria", fetch = FetchType.LAZY)
    private List<Proveedores> proveedoresList;

    public CategoriasServicios() {
    }

    public CategoriasServicios(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriasServicios(Integer idCategoria, String descripcion,String ruta) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriasServicios)) {
            return false;
        }
        CategoriasServicios other = (CategoriasServicios) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.CategoriasServicios[ idCategoria=" + idCategoria + " ]";
    }
    
}
