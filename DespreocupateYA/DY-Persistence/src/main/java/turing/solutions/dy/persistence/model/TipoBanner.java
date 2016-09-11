/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "tipo_banner", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBanner.findAll", query = "SELECT t FROM TipoBanner t"),
    @NamedQuery(name = "TipoBanner.findByIdTipobanner", query = "SELECT t FROM TipoBanner t WHERE t.idTipobanner = :idTipobanner"),
    @NamedQuery(name = "TipoBanner.findByDescripcion", query = "SELECT t FROM TipoBanner t WHERE t.descripcion = :descripcion")})
public class TipoBanner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPOBANNER")
    private Integer idTipobanner;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "UBICACION_BANNER_ID_UBICACION", referencedColumnName = "ID_UBICACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UbicacionBanner ubicacionBannerIdUbicacion;

    public TipoBanner() {
    }

    public TipoBanner(Integer idTipobanner) {
        this.idTipobanner = idTipobanner;
    }

    public Integer getIdTipobanner() {
        return idTipobanner;
    }

    public void setIdTipobanner(Integer idTipobanner) {
        this.idTipobanner = idTipobanner;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UbicacionBanner getUbicacionBannerIdUbicacion() {
        return ubicacionBannerIdUbicacion;
    }

    public void setUbicacionBannerIdUbicacion(UbicacionBanner ubicacionBannerIdUbicacion) {
        this.ubicacionBannerIdUbicacion = ubicacionBannerIdUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipobanner != null ? idTipobanner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBanner)) {
            return false;
        }
        TipoBanner other = (TipoBanner) object;
        if ((this.idTipobanner == null && other.idTipobanner != null) || (this.idTipobanner != null && !this.idTipobanner.equals(other.idTipobanner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.TipoBanner[ idTipobanner=" + idTipobanner + " ]";
    }
    
}
