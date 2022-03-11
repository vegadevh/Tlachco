package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

public class Comentario {
	
	@Id
	@Column(name="id_comentario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_comentario;
	
	@Column(name="comentario")
	private String comentario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_articulo")
	private Articulo articulo;
	
	@Transient
	private Integer id_articulo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Transient
	private Integer id_usuario;

}
