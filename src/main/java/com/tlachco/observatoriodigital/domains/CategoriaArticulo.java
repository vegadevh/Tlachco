package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CategoriaArticulo {
	
	@Id
	@Column(name="id_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_categoria;
	
	@Column(name="categoria")
	private String categoria;

}
