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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "tipo_telefono", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t"),
    @NamedQuery(name = "TipoTelefono.findByIdTipotelefono", query = "SELECT t FROM TipoTelefono t WHERE t.idTipotelefono = :idTipotelefono"),
    @NamedQuery(name = "TipoTelefono.findByDescripcion", query = "SELECT t FROM TipoTelefono t WHERE t.descripcion = :descripcion")})
public class TipoTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPOTELEFONO")
    private Integer idTipotelefono;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTelefonoIdTipotelefono", fetch = FetchType.LAZY)
    private List<Telefonos> telefonosList;

    public TipoTelefono() {
    }

    public TipoTelefono(Integer idTipotelefono) {
        this.idTipotelefono = idTipotelefono;
    }

    public TipoTelefono(Integer idTipotelefono, String descripcion) {
        this.idTipotelefono = idTipotelefono;
        this.descripcion = descripcion;
    }

    public Integer getIdTipotelefono() {
        return idTipotelefono;
    }

    public void setIdTipotelefono(Integer idTipotelefono) {
        this.idTipotelefono = idTipotelefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Telefonos> getTelefonosList() {
        return telefonosList;
    }

    public void setTelefonosList(List<Telefonos> telefonosList) {
        this.telefonosList = telefonosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipotelefono != null ? idTipotelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.idTipotelefono == null && other.idTipotelefono != null) || (this.idTipotelefono != null && !this.idTipotelefono.equals(other.idTipotelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.TipoTelefono[ idTipotelefono=" + idTipotelefono + " ]";
    }
    
}
