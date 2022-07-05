package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "public", name = "ARCHIVO")
public class Archivo {

	@Id
	@NotEmpty
	@Column(name = "id_archivo")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_archivo;

	// @Id
	// @NotEmpty
	// @Column(name="id_archivo")
	// private String id_archivo;
	@Column(name = "tipo")
	private String tipo;

	@Column(name = "nombre")
	private String nombre;

	// @Lob
	// @Type(type="org.hibernate.type.BinaryType")
	@Column(name = "contenido")
	private byte[] contenido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private CategoriaPublicacion categoriaPublicacion;

	@Transient
	private Integer id_categoria;

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

	public Archivo() {

	}

	public Archivo(@NotEmpty String id_archivo, String tipo, String nombre, byte[] contenido,
			CategoriaPublicacion categoriaPublicacion, Integer id_categoria) {
		super();
		this.id_archivo = id_archivo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.contenido = contenido;
		this.categoriaPublicacion = categoriaPublicacion;
		this.id_categoria = id_categoria;
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

	public String getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(String id_archivo) {
		this.id_archivo = id_archivo;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

}
