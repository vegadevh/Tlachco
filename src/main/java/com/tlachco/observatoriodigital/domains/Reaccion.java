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

@Entity
@Table(schema = "public", name = "REACCION")
public class Reaccion {

	@Id
	@Column(name = "id_reaccion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_reaccion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_publicacion")
	private Publicacion publicacion;

	@Transient
	private Integer id_publicacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario username;

	@Transient
	private Integer usuario;

	public Integer getId_reaccion() {
		return id_reaccion;
	}

	public void setId_reaccion(Integer id_reaccion) {
		this.id_reaccion = id_reaccion;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Integer getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(Integer id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

	public Usuario getUsername() {
		return username;
	}

	public void setUsername(Usuario username) {
		this.username = username;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

}
