package com.tlachco.observatoriodigital.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.services.IPublicacionService;

@Controller
@RequestMapping("/post")
public class TeacherCrontroller {

	@Autowired
	public IPublicacionService publicacionService;

	@RequestMapping("/aceptar/{id_publicacion}")
	public String aceptarPublicacion(@ModelAttribute Publicacion publicacion, BindingResult result,
			@PathVariable(value = "id_publicacion") String id_publicacion, Principal principal, Model model) {
		Publicacion publicacionAux = publicacionService.findOne(Integer.parseInt(id_publicacion));
		publicacion.setTitulo(publicacionAux.getTitulo());
		publicacion.setContenido(publicacionAux.getContenido());
		publicacion.setFecha_publicacion(publicacionAux.getFecha_publicacion());
		publicacion.setEstado("Public");
		publicacion.setCategoriaPublicacion(publicacionAux.getCategoriaPublicacion());
		publicacion.setUsuario(publicacionAux.getUsuario());
		publicacion.setArchivo(publicacionAux.getArchivo());
		publicacion.setProfesor(publicacionAux.getProfesor());

		try {
			publicacionService.save(publicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/perfil/" + principal.getName();
	}

	@RequestMapping("/en-espera/{id_publicacion}")
	public String enEsperaPublicacion(@ModelAttribute Publicacion publicacion, BindingResult result,
			@PathVariable(value = "id_publicacion") String id_publicacion, Principal principal, Model model) {
		Publicacion publicacionAux = publicacionService.findOne(Integer.parseInt(id_publicacion));
		publicacion.setTitulo(publicacionAux.getTitulo());
		publicacion.setContenido(publicacionAux.getContenido());
		publicacion.setFecha_publicacion(publicacionAux.getFecha_publicacion());
		publicacion.setEstado("Reviewed");
		publicacion.setCategoriaPublicacion(publicacionAux.getCategoriaPublicacion());
		publicacion.setUsuario(publicacionAux.getUsuario());
		publicacion.setArchivo(publicacionAux.getArchivo());
		publicacion.setProfesor(publicacionAux.getProfesor());

		try {
			publicacionService.save(publicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/perfil/" + principal.getName();
	}

}
