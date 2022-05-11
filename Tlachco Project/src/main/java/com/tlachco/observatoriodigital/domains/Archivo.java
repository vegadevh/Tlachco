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
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "nombre")
	private String nombre;

//	@Lob
//	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "contenido")
	private byte[] contenido;
	
	public Archivo() {
		
	}

	public Archivo(String tipo, String nombre, byte[] contenido) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.contenido = contenido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
