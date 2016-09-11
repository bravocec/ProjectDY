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
@Table(name = "telefonos", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefonos.findAll", query = "SELECT t FROM Telefonos t"),
    @NamedQuery(name = "Telefonos.findByIdTelefono", query = "SELECT t FROM Telefonos t WHERE t.idTelefono = :idTelefono"),
    @NamedQuery(name = "Telefonos.findByTelefono", query = "SELECT t FROM Telefonos t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Telefonos.findByPrincipal", query = "SELECT t FROM Telefonos t WHERE t.principal = :principal")})
public class Telefonos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TELEFONO")
    private Integer idTelefono;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "PRINCIPAL")
    private Boolean principal;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosIdUsuario;
    @JoinColumn(name = "TIPO_TELEFONO_ID_TIPOTELEFONO", referencedColumnName = "ID_TIPOTELEFONO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoTelefono tipoTelefonoIdTipotelefono;
    @JoinColumn(name = "SUCURSALES_ID_SUCURSAL", referencedColumnName = "ID_SUCURSAL")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sucursales sucursalesIdSucursal;

    public Telefonos() {
    }

    public Telefonos(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Telefonos(Integer idTelefono, String telefono) {
        this.idTelefono = idTelefono;
        this.telefono = telefono;
    }

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public TipoTelefono getTipoTelefonoIdTipotelefono() {
        return tipoTelefonoIdTipotelefono;
    }

    public void setTipoTelefonoIdTipotelefono(TipoTelefono tipoTelefonoIdTipotelefono) {
        this.tipoTelefonoIdTipotelefono = tipoTelefonoIdTipotelefono;
    }

    public Sucursales getSucursalesIdSucursal() {
        return sucursalesIdSucursal;
    }

    public void setSucursalesIdSucursal(Sucursales sucursalesIdSucursal) {
        this.sucursalesIdSucursal = sucursalesIdSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefono != null ? idTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefonos)) {
            return false;
        }
        Telefonos other = (Telefonos) object;
        if ((this.idTelefono == null && other.idTelefono != null) || (this.idTelefono != null && !this.idTelefono.equals(other.idTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Telefonos[ idTelefono=" + idTelefono + " ]";
    }
    
}
