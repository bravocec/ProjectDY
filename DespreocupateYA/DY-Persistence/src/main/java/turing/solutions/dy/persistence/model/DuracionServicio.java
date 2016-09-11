/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "duracion_servicio", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DuracionServicio.findAll", query = "SELECT d FROM DuracionServicio d"),
    @NamedQuery(name = "DuracionServicio.findByIdDuracionservicio", query = "SELECT d FROM DuracionServicio d WHERE d.idDuracionservicio = :idDuracionservicio"),
    @NamedQuery(name = "DuracionServicio.findByFechaInicio", query = "SELECT d FROM DuracionServicio d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "DuracionServicio.findByFechaFin", query = "SELECT d FROM DuracionServicio d WHERE d.fechaFin = :fechaFin")})
public class DuracionServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DURACIONSERVICIO")
    private Integer idDuracionservicio;
    @Basic(optional = false)
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(mappedBy = "duracionServicioIdDuracionservicio", fetch = FetchType.LAZY)
    private List<Pedidos> pedidosList;

    public DuracionServicio() {
    }

    public DuracionServicio(Integer idDuracionservicio) {
        this.idDuracionservicio = idDuracionservicio;
    }

    public DuracionServicio(Integer idDuracionservicio, Date fechaInicio) {
        this.idDuracionservicio = idDuracionservicio;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdDuracionservicio() {
        return idDuracionservicio;
    }

    public void setIdDuracionservicio(Integer idDuracionservicio) {
        this.idDuracionservicio = idDuracionservicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDuracionservicio != null ? idDuracionservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuracionServicio)) {
            return false;
        }
        DuracionServicio other = (DuracionServicio) object;
        if ((this.idDuracionservicio == null && other.idDuracionservicio != null) || (this.idDuracionservicio != null && !this.idDuracionservicio.equals(other.idDuracionservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.DuracionServicio[ idDuracionservicio=" + idDuracionservicio + " ]";
    }
    
}
