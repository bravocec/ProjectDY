/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cat_prod_serv", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatProdServ.findAll", query = "SELECT c FROM CatProdServ c"),
    @NamedQuery(name = "CatProdServ.findByIdCatprodserv", query = "SELECT c FROM CatProdServ c WHERE c.idCatprodserv = :idCatprodserv"),
    @NamedQuery(name = "CatProdServ.findByDescripcion", query = "SELECT c FROM CatProdServ c WHERE c.descripcion = :descripcion")})
public class CatProdServ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATPRODSERV")
    private Integer idCatprodserv;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catProdServIdCatprodserv", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductosServicios> productosServiciosList;

    public CatProdServ() {
    }

    public CatProdServ(Integer idCatprodserv) {
        this.idCatprodserv = idCatprodserv;
    }

    public Integer getIdCatprodserv() {
        return idCatprodserv;
    }

    public void setIdCatprodserv(Integer idCatprodserv) {
        this.idCatprodserv = idCatprodserv;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<ProductosServicios> getProductosServiciosList() {
        return productosServiciosList;
    }

    public void setProductosServiciosList(List<ProductosServicios> productosServiciosList) {
        this.productosServiciosList = productosServiciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatprodserv != null ? idCatprodserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProdServ)) {
            return false;
        }
        CatProdServ other = (CatProdServ) object;
        if ((this.idCatprodserv == null && other.idCatprodserv != null) || (this.idCatprodserv != null && !this.idCatprodserv.equals(other.idCatprodserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.CatProdServ[ idCatprodserv=" + idCatprodserv + " ]";
    }
    
}
