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
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;
import com.tlachco.observatoriodigital.services.IVideoService;
import com.tlachco.observatoriodigital.services.VideoServiceImpl;

@Controller
@RequestMapping("/post")
public class ComentarioController {
	
	@Autowired
	public IPublicacionService publicacionService;
	
	@Autowired
	public IUsuarioService usuarioService;
	
	@Autowired
	public IComentarioService comentarioService;
	
	@RequestMapping("/validar-comentario")
	public String validar_post(@Valid @ModelAttribute Comentario comment, BindingResult result,
			Principal principal,@RequestParam String id_publicacion, HttpServletRequest request, Model model) {


		if (result.hasErrors()) {
			Publicacion publicacion = publicacionService.findOne(Integer.parseInt(id_publicacion));
			
			model.addAttribute("comment", comment);
			model.addAttribute("publicacion", publicacion);
			model.addAttribute("id_post", id_publicacion);

			return "redirect:/post/articuloDetail/{id_post}";
		} else {
			
			String id_usuario = principal.getName();

			Usuario propietario = new Usuario();
			propietario = usuarioService.findOne(id_usuario);

			comment.setPublicacion(publicacionService.findOne(Integer.parseInt(id_publicacion)));
			comment.setUsername(propietario);

			try {
				comentarioService.save(comment);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/post/articuloDetail/" + comment.getId_publicacion();
		}
	}

}
