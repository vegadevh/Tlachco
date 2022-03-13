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
@Table(schema="public", name="ARTICULOS")
public class Articulo {
	
	@Id
	@Column(name="id_articulo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_articulo;
	
	//@Column(name="autor")
	//private String autor;
	
	@Column(name="titulo")
	@Size(message="Como maximo puede ingresar 200 caracteres.", max=200)
	private String titulo;
	
	@Column(name="contenido")
	private String contenido;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="fecha_publicacion")
	private Date fecha_publicacion;
	
	@Column(name="tipo_articulo")
	private String tipo_articulo;
	
	//@Column(name="categoria")
	//private String categoria;
	
	//@Column(name="url")
	//private String url;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private CategoriaArticulo categoriaArticulo;
	
	@Transient
	private Integer id_categoria;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	private Usuario usuario;
	
	@Transient
	private Integer username;

	public Integer getId_articulo() {
		return id_articulo;
	}

	public void setId_articulo(Integer id_articulo) {
		this.id_articulo = id_articulo;
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

	public String getTipo_articulo() {
		return tipo_articulo;
	}

	public void setTipo_articulo(String tipo_articulo) {
		this.tipo_articulo = tipo_articulo;
	}

	public CategoriaArticulo getCategoriaArticulo() {
		return categoriaArticulo;
	}

	public void setCategoriaArticulo(CategoriaArticulo categoriaArticulo) {
		this.categoriaArticulo = categoriaArticulo;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getUsername() {
		return username;
	}

	public void setUsername(Integer username) {
		this.username = username;
	}
	
	

}
