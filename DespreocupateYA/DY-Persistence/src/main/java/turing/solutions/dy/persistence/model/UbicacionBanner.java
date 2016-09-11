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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "ubicacion_banner", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionBanner.findAll", query = "SELECT u FROM UbicacionBanner u"),
    @NamedQuery(name = "UbicacionBanner.findByIdUbicacion", query = "SELECT u FROM UbicacionBanner u WHERE u.idUbicacion = :idUbicacion"),
    @NamedQuery(name = "UbicacionBanner.findByDescripcion", query = "SELECT u FROM UbicacionBanner u WHERE u.descripcion = :descripcion")})
public class UbicacionBanner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UBICACION")
    private Integer idUbicacion;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubicacionBannerIdUbicacion", fetch = FetchType.LAZY)
    private List<TipoBanner> tipoBannerList;
    @OneToOne(mappedBy = "ubicacionBannerIdUbicacion", fetch = FetchType.LAZY)
    private Banners banner;

    public UbicacionBanner() {
    }

    public UbicacionBanner(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<TipoBanner> getTipoBannerList() {
        return tipoBannerList;
    }

    public void setTipoBannerList(List<TipoBanner> tipoBannerList) {
        this.tipoBannerList = tipoBannerList;
    }

    public Banners getBanner() {
        return banner;
    }

    public void setBanner(Banners banner) {
        this.banner = banner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbicacion != null ? idUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionBanner)) {
            return false;
        }
        UbicacionBanner other = (UbicacionBanner) object;
        if ((this.idUbicacion == null && other.idUbicacion != null) || (this.idUbicacion != null && !this.idUbicacion.equals(other.idUbicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.UbicacionBanner[ idUbicacion=" + idUbicacion + " ]";
    }
    
}
