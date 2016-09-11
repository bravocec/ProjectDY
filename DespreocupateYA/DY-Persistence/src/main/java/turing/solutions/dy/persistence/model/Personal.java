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
import javax.persistence.Lob;
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
@Table(name = "personal", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByIdPersonal", query = "SELECT p FROM Personal p WHERE p.idPersonal = :idPersonal"),
    @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Personal.findByApPaterno", query = "SELECT p FROM Personal p WHERE p.apPaterno = :apPaterno"),
    @NamedQuery(name = "Personal.findByApMaterno", query = "SELECT p FROM Personal p WHERE p.apMaterno = :apMaterno"),
    @NamedQuery(name = "Personal.findByTelefonoPrincipal", query = "SELECT p FROM Personal p WHERE p.telefonoPrincipal = :telefonoPrincipal"),
    @NamedQuery(name = "Personal.findByTelefonoAux", query = "SELECT p FROM Personal p WHERE p.telefonoAux = :telefonoAux"),
    @NamedQuery(name = "Personal.findByDireccion", query = "SELECT p FROM Personal p WHERE p.direccion = :direccion")})
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSONAL")
    private Integer idPersonal;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "AP_PATERNO")
    private String apPaterno;
    @Column(name = "AP_MATERNO")
    private String apMaterno;
    @Basic(optional = false)
    @Lob
    @Column(name = "FOTO",columnDefinition="blob")
    private byte[] foto;
    @Basic(optional = false)
    @Column(name = "TELEFONO_PRINCIPAL")
    private String telefonoPrincipal;
    @Column(name = "TELEFONO_AUX")
    private String telefonoAux;
    @Lob
    @Column(name = "IDENTIFICACION",columnDefinition="blob")
    private byte[] identificacion;
    @Lob
    @Column(name = "COMPROBANTE_DOMICILIO",columnDefinition="blob")
    private byte[] comprobanteDomicilio;
    @Column(name = "DIRECCION")
    private String direccion;
    @JoinColumn(name = "PRODUCTOS_SERVICIOS_ID_PRODSERV", referencedColumnName = "ID_PRODSERV")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductosServicios productosServiciosIdProdserv;

    public Personal() {
    }

    public Personal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Personal(Integer idPersonal, String nombres, String apPaterno, byte[] foto, String telefonoPrincipal) {
        this.idPersonal = idPersonal;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.foto = foto;
        this.telefonoPrincipal = telefonoPrincipal;
    }

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    public void setTelefonoPrincipal(String telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }

    public String getTelefonoAux() {
        return telefonoAux;
    }

    public void setTelefonoAux(String telefonoAux) {
        this.telefonoAux = telefonoAux;
    }

    public byte[] getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(byte[] identificacion) {
        this.identificacion = identificacion;
    }

    public byte[] getComprobanteDomicilio() {
        return comprobanteDomicilio;
    }

    public void setComprobanteDomicilio(byte[] comprobanteDomicilio) {
        this.comprobanteDomicilio = comprobanteDomicilio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ProductosServicios getProductosServiciosIdProdserv() {
        return productosServiciosIdProdserv;
    }

    public void setProductosServiciosIdProdserv(ProductosServicios productosServiciosIdProdserv) {
        this.productosServiciosIdProdserv = productosServiciosIdProdserv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonal != null ? idPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idPersonal == null && other.idPersonal != null) || (this.idPersonal != null && !this.idPersonal.equals(other.idPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Personal[ idPersonal=" + idPersonal + " ]";
    }
    
}
