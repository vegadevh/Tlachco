package com.tlachco.observatoriodigital.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Articulo {
	
	@Id
	@Column(name="id_articulo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_articulo;
	
	//@Column(name="autor")
	//private String autor;
	
	@Column(name="titulo")
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
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Transient
	private Integer id_usuario;

}
