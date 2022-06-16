package com.tlachco.observatoriodigital.dto;

public class PublicacionesDTO {
	
	private String id_publicacion;
	
	private String titulo;
	
	private String contenido;
	
	private String fecha_publicacion;
	
	private String propietario;
	
	private String estado;
	
	private String nombre;
	
	private String apellido;

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(String id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
