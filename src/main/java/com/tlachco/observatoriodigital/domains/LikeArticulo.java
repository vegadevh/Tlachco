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
@Table(schema="public", name="LIKES")
public class LikeArticulo {
	
	@Id
	@Column(name="id_like")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_like;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_articulo")
	private Articulo articulo;
	
	@Transient
	private Integer id_articulo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	private Usuario usuario;
	
	@Transient
	private Integer username;

	public Integer getId_like() {
		return id_like;
	}

	public void setId_like(Integer id_like) {
		this.id_like = id_like;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getId_articulo() {
		return id_articulo;
	}

	public void setId_articulo(Integer id_articulo) {
		this.id_articulo = id_articulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getUsername() {
		return username;
	}

	public void setUsername(Integer username) {
		this.username = username;
	}
	
	

}
