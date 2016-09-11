/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "usuarios", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuarios.findByAlias", query = "SELECT u FROM Usuarios u WHERE u.alias = :alias"),
    @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email"),
    @NamedQuery(name = "Usuarios.findByEmailAlternativo", query = "SELECT u FROM Usuarios u WHERE u.emailAlternativo = :emailAlternativo"),
    @NamedQuery(name = "Usuarios.findByRfc", query = "SELECT u FROM Usuarios u WHERE u.rfc = :rfc"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByActivo", query = "SELECT u FROM Usuarios u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuarios.findByFechaCreacion", query = "SELECT u FROM Usuarios u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuarios.findByFechaModificacion", query = "SELECT u FROM Usuarios u WHERE u.fechaModificacion = :fechaModificacion")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "ALIAS")
    private String alias;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EMAIL_ALTERNATIVO")
    private String emailAlternativo;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private boolean activo;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    /**
     * Relaciones
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", 
            joinColumns = {@JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")}, 
            inverseJoinColumns = {@JoinColumn(name = "ROLES_ID_ROL", referencedColumnName = "ID_ROL")})
    private List<Roles> rolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<Telefonos> telefonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<Tarjetas> tarjetasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<Domicilios> domiciliosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosIdUsuario", fetch = FetchType.LAZY)
    private List<Pedidos> pedidosList;
    @JoinColumn(name = "PROVEEDORES_ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Proveedores proveedoresIdProveedor;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombres, String apellidos, String apMaterno, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @XmlTransient
    public List<Telefonos> getTelefonosList() {
        return telefonosList;
    }

    public void setTelefonosList(List<Telefonos> telefonosList) {
        this.telefonosList = telefonosList;
    }

    @XmlTransient
    public List<Tarjetas> getTarjetasList() {
        return tarjetasList;
    }

    public void setTarjetasList(List<Tarjetas> tarjetasList) {
        this.tarjetasList = tarjetasList;
    }

    @XmlTransient
    public List<Domicilios> getDomiciliosList() {
        return domiciliosList;
    }

    public void setDomiciliosList(List<Domicilios> domiciliosList) {
        this.domiciliosList = domiciliosList;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    public Proveedores getProveedoresIdProveedor() {
        return proveedoresIdProveedor;
    }

    public void setProveedoresIdProveedor(Proveedores proveedoresIdProveedor) {
        this.proveedoresIdProveedor = proveedoresIdProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "Usuarios{" + "idUsuario=" + idUsuario + ", nombres=" + nombres + ", apellidos=" + apellidos +  ", alias=" + alias + ", email=" + email + ", emailAlternativo=" + emailAlternativo + ", rfc=" + rfc + ", password=" + password + ", activo=" + activo + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion;
        
        if (rolesList != null) {
            str += ", rolesList=[";
            for (Roles rol : rolesList) {
                str += rol.getDescRol() + ",";
            }
            str += "]}";
        }else{
            str += "}";
        }

        
        return str;
    }

}
