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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema="public", name="TAG")
public class TagPublicacion {
	
	@Id
	@Column(name="id_tag")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tag;
	
	@NotEmpty
	@Column(name="tag")
	private String tag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_publicacion")
	private Publicacion publicacion;
	
	@Transient
	private Integer id_publicacion;

	public Integer getId_tag() {
		return id_tag;
	}

	public void setId_tag(Integer id_tag) {
		this.id_tag = id_tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
