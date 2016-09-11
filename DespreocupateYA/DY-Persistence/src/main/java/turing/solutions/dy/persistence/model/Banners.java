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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "banners", catalog = "turing_gapper", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banners.findAll", query = "SELECT b FROM Banners b"),
    @NamedQuery(name = "Banners.findByIdBanner", query = "SELECT b FROM Banners b WHERE b.idBanner = :idBanner"),
    @NamedQuery(name = "Banners.findByTitulo", query = "SELECT b FROM Banners b WHERE b.titulo = :titulo"),
    @NamedQuery(name = "Banners.findBySubTitulo", query = "SELECT b FROM Banners b WHERE b.subTitulo = :subTitulo"),
    @NamedQuery(name = "Banners.findByDescripcion", query = "SELECT b FROM Banners b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "Banners.findByUrl1", query = "SELECT b FROM Banners b WHERE b.url1 = :url1"),
    @NamedQuery(name = "Banners.findByUrl2", query = "SELECT b FROM Banners b WHERE b.url2 = :url2"),
    @NamedQuery(name = "Banners.findByListaDescripciones", query = "SELECT b FROM Banners b WHERE b.listaDescripciones = :listaDescripciones"),
    @NamedQuery(name = "Banners.findByActivo", query = "SELECT b FROM Banners b WHERE b.activo = :activo")})
public class Banners implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BANNER")
    private Integer idBanner;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "SUB_TITULO")
    private String subTitulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Lob
    @Column(name = "IMG1",columnDefinition="blob")
    private byte[] img1;
    @Lob
    @Column(name = "IMG2",columnDefinition="blob" )
    private byte[] img2;
    @Lob
    @Column(name = "IMG3",columnDefinition="blob")
    private byte[] img3;
    @Column(name = "URL1")
    private String url1;
    @Column(name = "URL2")
    private String url2;
    @Column(name = "LISTA_DESCRIPCIONES")
    private String listaDescripciones;
    @Lob
    @Column(name = "IMAGEN_FONDO",columnDefinition="blob")
    private byte[] imagenFondo;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @JoinColumn(name = "UBICACION_BANNER_ID_UBICACION", referencedColumnName = "ID_UBICACION")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UbicacionBanner ubicacionBannerIdUbicacion;

    public Banners() {
    }

    public Banners(Integer idBanner) {
        this.idBanner = idBanner;
    }

    public Integer getIdBanner() {
        return idBanner;
    }

    public void setIdBanner(Integer idBanner) {
        this.idBanner = idBanner;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

    public byte[] getImg3() {
        return img3;
    }

    public void setImg3(byte[] img3) {
        this.img3 = img3;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getListaDescripciones() {
        return listaDescripciones;
    }

    public void setListaDescripciones(String listaDescripciones) {
        this.listaDescripciones = listaDescripciones;
    }

    public byte[] getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(byte[] imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UbicacionBanner getUbicacionBannerIdUbicacion() {
        return ubicacionBannerIdUbicacion;
    }

    public void setUbicacionBannerIdUbicacion(UbicacionBanner ubicacionBannerIdUbicacion) {
        this.ubicacionBannerIdUbicacion = ubicacionBannerIdUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanner != null ? idBanner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banners)) {
            return false;
        }
        Banners other = (Banners) object;
        if ((this.idBanner == null && other.idBanner != null) || (this.idBanner != null && !this.idBanner.equals(other.idBanner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turing.solutions.dy.persistence.model.Banners[ idBanner=" + idBanner + " ]";
    }
    
}
