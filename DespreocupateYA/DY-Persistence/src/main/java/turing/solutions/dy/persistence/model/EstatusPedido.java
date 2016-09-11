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
@Table(name = "estatus_pedido", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusPedido.findAll", query = "SELECT e FROM EstatusPedido e"),
    @NamedQuery(name = "EstatusPedido.findByIdEstatuspedido", query = "SELECT e FROM EstatusPedido e WHERE e.idEstatuspedido = :idEstatuspedido"),
    @NamedQuery(name = "EstatusPedido.findByDescripcion", query = "SELECT e FROM EstatusPedido e WHERE e.descripcion = :descripcion")})
public class EstatusPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTATUSPEDIDO")
    private Integer idEstatuspedido;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusPedidoIdEstatuspedido", fetch = FetchType.LAZY)
    private List<Pedidos> pedidosList;

    public EstatusPedido() {
    }

    public EstatusPedido(Integer idEstatuspedido) {
        this.idEstatuspedido = idEstatuspedido;
    }

    public Integer getIdEstatuspedido() {
        return idEstatuspedido;
    }

    public void setIdEstatuspedido(Integer idEstatuspedido) {
        this.idEstatuspedido = idEstatuspedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idEstatuspedido != null ? idEstatuspedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusPedido)) {
            return false;
        }
        EstatusPedido other = (EstatusPedido) object;
        if ((this.idEstatuspedido == null && other.idEstatuspedido != null) || (this.idEstatuspedido != null && !this.idEstatuspedido.equals(other.idEstatuspedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.EstatusPedido[ idEstatuspedido=" + idEstatuspedido + " ]";
    }
    
}
