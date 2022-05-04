package com.tlachco.observatoriodigital.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "public", name = "PUBLICACION")
public class Publicacion {

	@Id
	@Column(name = "id_publicacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_publicacion;

	@Column(name = "titulo")
	@Size(message = "Como maximo puede ingresar 200 caracteres.", max = 200)
	private String titulo;

	@Column(name = "contenido")
	private String contenido;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_publicacion")
	private Date fecha_publicacion;
	
	@Column(name="estado")
	private String estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private CategoriaPublicacion categoriaPublicacion;

	@Transient
	private Integer id_categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario propietario;

	@Transient
	private Integer usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_archivo")
	private Archivo archivo;

	@Transient
	private Integer id_archivo;

	public Integer getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(Integer id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public CategoriaPublicacion getCategoriaPublicacion() {
		return categoriaPublicacion;
	}

	public void setCategoriaPublicacion(CategoriaPublicacion categoriaPublicacion) {
		this.categoriaPublicacion = categoriaPublicacion;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public Integer getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(Integer id_archivo) {
		this.id_archivo = id_archivo;
	}

	
}
