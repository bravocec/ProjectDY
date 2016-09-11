/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "estatus_proveedor", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusProveedor.findAll", query = "SELECT c FROM EstatusProveedor c"),
    @NamedQuery(name = "EstatusProveedor.findById", query = "SELECT c FROM EstatusProveedor c WHERE c.idEstatusProveedor = :idEstatusProveedor"),
    @NamedQuery(name = "EstatusProveedor.findByDescripcion", query = "SELECT c FROM EstatusProveedor c WHERE c.descripcion = :descripcion")})
public class EstatusProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTATUSPROVEEDOR")
    private Integer idEstatusProveedor;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusProveedorId", fetch = FetchType.LAZY)
    private List<Proveedores> proveedoresList;
    
    public EstatusProveedor(){
    
    }

    public EstatusProveedor(Integer idEstatusProveedor, String descripcion) {
        this.idEstatusProveedor = idEstatusProveedor;
        this.descripcion = descripcion;
    }
    
    public Integer getIdEstatusProveedor() {
        return idEstatusProveedor;
    }

    public void setIdEstatusProveedor(Integer idEstatusProveedor) {
        this.idEstatusProveedor = idEstatusProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idEstatusProveedor);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstatusProveedor other = (EstatusProveedor) obj;
        if (!Objects.equals(this.idEstatusProveedor, other.idEstatusProveedor)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
