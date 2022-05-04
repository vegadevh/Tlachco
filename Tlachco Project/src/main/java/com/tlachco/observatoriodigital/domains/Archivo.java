package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(schema = "public", name = "ARCHIVO")
public class Archivo {

	@Id
	@Column(name = "id_archivo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_archivo;
	
//	@Id
//	@NotEmpty
//	@Column(name="id_archivo")
//	private String id_archivo;

	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "contenido")
	private byte[] contenido;
	
	public Archivo() {
		
	}

	public Archivo(byte[] contenido) {
		super();
//		this.id_archivo = id_archivo;
		this.contenido = contenido;
	}

	public Integer getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(Integer id_archivo) {
		this.id_archivo = id_archivo;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	

	

}
