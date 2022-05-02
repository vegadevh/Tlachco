package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="CATEGORIA")
public class CategoriaPublicacion {
	
	@Id
	@Column(name="id_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_categoria;
	
	@Column(name="categoria")
	@Size(message="Como maximo puede ingresar 30 caracteres.", max=30)
	private String categoria;

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
