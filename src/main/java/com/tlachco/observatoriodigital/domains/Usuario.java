package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario {
	
	@Id
	@Column(name="id_ussuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_ussuario;
	
	@Id
	@NotEmpty
	@Column(name="username")
	@Size(message="Como maximo puede ingresar 50 caracteres.", max=50)
	private String username;
	
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

}
