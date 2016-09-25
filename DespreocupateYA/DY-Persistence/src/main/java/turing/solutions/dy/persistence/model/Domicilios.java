/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "domicilios", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domicilios.findAll", query = "SELECT d FROM Domicilios d"),
    @NamedQuery(name = "Domicilios.findByIdDomicilio", query = "SELECT d FROM Domicilios d WHERE d.idDomicilio = :idDomicilio"),
    @NamedQuery(name = "Domicilios.findByCalle", query = "SELECT d FROM Domicilios d WHERE d.calle = :calle"),
    @NamedQuery(name = "Domicilios.findByNumInt", query = "SELECT d FROM Domicilios d WHERE d.numInt = :numInt"),
    @NamedQuery(name = "Domicilios.findByNumExt", query = "SELECT d FROM Domicilios d WHERE d.numExt = :numExt"),
    @NamedQuery(name = "Domicilios.findByColonia", query = "SELECT d FROM Domicilios d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "Domicilios.findByDelegacionMunicipio", query = "SELECT d FROM Domicilios d WHERE d.delegacionMunicipio = :delegacionMunicipio"),
    @NamedQuery(name = "Domicilios.findByCp", query = "SELECT d FROM Domicilios d WHERE d.cp = :cp"),
    @NamedQuery(name = "Domicilios.findByEntreCalles", query = "SELECT d FROM Domicilios d WHERE d.entreCalles = :entreCalles"),
    @NamedQuery(name = "Domicilios.findByDescripcion", query = "SELECT d FROM Domicilios d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Domicilios.findByPrincipal", query = "SELECT d FROM Domicilios d WHERE d.principal = :principal"),
    @NamedQuery(name = "Domicilios.findByLatitud", query = "SELECT d FROM Domicilios d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Domicilios.findByLongitud", query = "SELECT d FROM Domicilios d WHERE d.longitud = :longitud")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domicilios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOMICILIO")
    private Integer idDomicilio;
    @Basic(optional = false)
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "NUM_INT")
    private String numInt;
    @Basic(optional = false)
    @Column(name = "NUM_EXT")
    private String numExt;
    @Basic(optional = false)
    @Column(name = "COLONIA")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "DELEGACION_MUNICIPIO")
    private String delegacionMunicipio;
    @Basic(optional = false)
    @Column(name = "CP")
    private String cp;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "ENTRE_CALLES")
    private String entreCalles;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PRINCIPAL")
    private String principal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUD")
    private Double latitud;
    @Column(name = "LONGITUD")
    private Double longitud;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuarios usuariosIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domiciliosIdDomicilio", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sucursales> sucursalesList;

    public Domicilios() {
    }

    public Domicilios(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Domicilios(Integer idDomicilio, String calle, String numInt, String colonia, String delegacionMunicipio, String cp,String estado) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
        this.numInt = numInt;
        this.colonia = colonia;
        this.delegacionMunicipio = delegacionMunicipio;
        this.cp = cp;
        this.estado = estado;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getDelegacionMunicipio() {
        return delegacionMunicipio;
    }

    public void setDelegacionMunicipio(String delegacionMunicipio) {
        this.delegacionMunicipio = delegacionMunicipio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEntreCalles() {
        return entreCalles;
    }

    public void setEntreCalles(String entreCalles) {
        this.entreCalles = entreCalles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    @XmlTransient
    public List<Sucursales> getSucursalesList() {
        return sucursalesList;
    }

    public void setSucursalesList(List<Sucursales> sucursalesList) {
        this.sucursalesList = sucursalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomicilio != null ? idDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domicilios)) {
            return false;
        }
        Domicilios other = (Domicilios) object;
        if ((this.idDomicilio == null && other.idDomicilio != null) || (this.idDomicilio != null && !this.idDomicilio.equals(other.idDomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domicilios{" + "idDomicilio=" + idDomicilio + ", calle=" + calle + ", numInt=" + numInt + ", numExt=" + numExt + ", colonia=" + colonia + ", delegacionMunicipio=" + delegacionMunicipio + ", cp=" + cp + ", estado=" + estado + ", entreCalles=" + entreCalles + ", descripcion=" + descripcion + ", principal=" + principal + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }

}
