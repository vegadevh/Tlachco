package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "ARCHIVO")
public class Archivo {

	@Id
	@Column(name = "id_articulo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_archivo;

	// Revision de tipos
	@Column(name = "contenido")
	private String contenido;

	public Integer getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(Integer id_archivo) {
		this.id_archivo = id_archivo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
