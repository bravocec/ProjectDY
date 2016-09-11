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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "pedidos", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdPedido", query = "SELECT p FROM Pedidos p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedidos.findByFechaPedido", query = "SELECT p FROM Pedidos p WHERE p.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "Pedidos.findByFechaPedidoFin", query = "SELECT p FROM Pedidos p WHERE p.fechaPedidoFin = :fechaPedidoFin"),
    @NamedQuery(name = "Pedidos.findByCostoTotal", query = "SELECT p FROM Pedidos p WHERE p.costoTotal = :costoTotal"),
    @NamedQuery(name = "Pedidos.findByDescripcion", query = "SELECT p FROM Pedidos p WHERE p.descripcion = :descripcion")})
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;
    @Basic(optional = false)
    @Column(name = "FECHA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Column(name = "FECHA_PEDIDO_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedidoFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO_TOTAL")
    private Double costoTotal;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuariosIdUsuario;
    @JoinColumn(name = "TARJETAS_ID_TARJETA", referencedColumnName = "ID_TARJETA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarjetas tarjetasIdTarjeta;
    @JoinColumn(name = "ESTATUS_PEDIDO_ID_ESTATUSPEDIDO", referencedColumnName = "ID_ESTATUSPEDIDO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusPedido estatusPedidoIdEstatuspedido;
    @JoinColumn(name = "DURACION_SERVICIO_ID_DURACIONSERVICIO", referencedColumnName = "ID_DURACIONSERVICIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DuracionServicio duracionServicioIdDuracionservicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidos", fetch = FetchType.LAZY)
    private List<ProductosPedido> productosPedidoList;

    public Pedidos() {
    }

    public Pedidos(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos(Integer idPedido, Date fechaPedido) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaPedidoFin() {
        return fechaPedidoFin;
    }

    public void setFechaPedidoFin(Date fechaPedidoFin) {
        this.fechaPedidoFin = fechaPedidoFin;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public Tarjetas getTarjetasIdTarjeta() {
        return tarjetasIdTarjeta;
    }

    public void setTarjetasIdTarjeta(Tarjetas tarjetasIdTarjeta) {
        this.tarjetasIdTarjeta = tarjetasIdTarjeta;
    }

    public EstatusPedido getEstatusPedidoIdEstatuspedido() {
        return estatusPedidoIdEstatuspedido;
    }

    public void setEstatusPedidoIdEstatuspedido(EstatusPedido estatusPedidoIdEstatuspedido) {
        this.estatusPedidoIdEstatuspedido = estatusPedidoIdEstatuspedido;
    }

    public DuracionServicio getDuracionServicioIdDuracionservicio() {
        return duracionServicioIdDuracionservicio;
    }

    public void setDuracionServicioIdDuracionservicio(DuracionServicio duracionServicioIdDuracionservicio) {
        this.duracionServicioIdDuracionservicio = duracionServicioIdDuracionservicio;
    }

    @XmlTransient
    public List<ProductosPedido> getProductosPedidoList() {
        return productosPedidoList;
    }

    public void setProductosPedidoList(List<ProductosPedido> productosPedidoList) {
        this.productosPedidoList = productosPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Pedidos[ idPedido=" + idPedido + " ]";
    }
    
}
