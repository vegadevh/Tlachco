package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="ROL")
public class Rol {
	
	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_rol;
	
	@Column(name="rol")
	@NotEmpty
	@Size(message="Como maximo puede ingresar 20 caracteres.", max=20)
	private String rol;

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
