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
@Table(name = "tipos_tarjeta", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposTarjeta.findAll", query = "SELECT t FROM TiposTarjeta t"),
    @NamedQuery(name = "TiposTarjeta.findByIdTipotarjeta", query = "SELECT t FROM TiposTarjeta t WHERE t.idTipotarjeta = :idTipotarjeta"),
    @NamedQuery(name = "TiposTarjeta.findByDescripcion", query = "SELECT t FROM TiposTarjeta t WHERE t.descripcion = :descripcion")})
public class TiposTarjeta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPOTARJETA")
    private Integer idTipotarjeta;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "IMAGEN",columnDefinition="blob")
    private byte[] imagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposTarjetaIdTipotarjeta", fetch = FetchType.LAZY)
    private List<Tarjetas> tarjetasList;

    public TiposTarjeta() {
    }

    public TiposTarjeta(Integer idTipotarjeta) {
        this.idTipotarjeta = idTipotarjeta;
    }

    public TiposTarjeta(Integer idTipotarjeta, String descripcion, byte[] imagen) {
        this.idTipotarjeta = idTipotarjeta;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Integer getIdTipotarjeta() {
        return idTipotarjeta;
    }

    public void setIdTipotarjeta(Integer idTipotarjeta) {
        this.idTipotarjeta = idTipotarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public List<Tarjetas> getTarjetasList() {
        return tarjetasList;
    }

    public void setTarjetasList(List<Tarjetas> tarjetasList) {
        this.tarjetasList = tarjetasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipotarjeta != null ? idTipotarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposTarjeta)) {
            return false;
        }
        TiposTarjeta other = (TiposTarjeta) object;
        if ((this.idTipotarjeta == null && other.idTipotarjeta != null) || (this.idTipotarjeta != null && !this.idTipotarjeta.equals(other.idTipotarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.TiposTarjeta[ idTipotarjeta=" + idTipotarjeta + " ]";
    }
    
}
