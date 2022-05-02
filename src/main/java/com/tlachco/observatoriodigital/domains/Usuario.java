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
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="USUARIO")
public class Usuario {
	
	@Id
	@NotEmpty
	@Column(name="usuario")
	@Size(message="Como maximo puede ingresar 50 caracteres.", max=50)
	private String usuario;
	
	@NotEmpty
	@Column(name="password")
	private String password;
	
	
	@Column(name="enabled_u")
	private Boolean enabled_u;
	
	@NotEmpty
	@Column(name="nombre")
	@Size(message="Como maximo puede ingresar 80 caracteres.", max=80)
	private String nombre;
	
	@NotEmpty
	@Column(name="apellido")
	@Size(message="Como maximo puede ingresar 80 caracteres.", max=80)
	private String apellido;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	@Transient
	private Integer id_rol;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled_u() {
		return enabled_u;
	}

	public void setEnabled_u(Boolean enabled_u) {
		this.enabled_u = enabled_u;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	
	

}
