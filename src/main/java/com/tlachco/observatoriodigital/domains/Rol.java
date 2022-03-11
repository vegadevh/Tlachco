package com.tlachco.observatoriodigital.domains;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Rol {
	
	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_rol;
	
	@Column(name="rol")
	@NotEmpty
	private String rol;

}
