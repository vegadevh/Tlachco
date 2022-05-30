package com.tlachco.observatoriodigital.domains;

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

@Entity
@Table(schema = "public", name = "VIDEO")
public class Video {

	@Id
	@Column(name = "id_video")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_video;

	@Column(name = "enlace")
	@Size(message = "Como maximo puede ingresar 200 caracteres.", max = 200)
	private String enlace;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private CategoriaPublicacion categoriaPublicacion;

	@Transient
	private Integer id_categoria;

	public Integer getId_video() {
		return id_video;
	}

	public void setId_video(Integer id_video) {
		this.id_video = id_video;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

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
	
	
	
}
