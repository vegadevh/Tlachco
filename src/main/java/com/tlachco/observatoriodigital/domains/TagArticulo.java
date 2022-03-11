package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

public class TagArticulo {
	
	@Id
	@Column(name="id_tag")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tag;
	
	@NotEmpty
	@Column(name="tag")
	private String tag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_articulo")
	private Articulo articulo;
	
	@Transient
	private Integer id_articulo;

}
