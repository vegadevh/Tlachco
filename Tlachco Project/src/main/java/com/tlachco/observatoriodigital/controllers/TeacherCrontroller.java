package com.tlachco.observatoriodigital.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.dto.ComentarioDTO;
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;
import com.tlachco.observatoriodigital.services.IVideoService;

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
