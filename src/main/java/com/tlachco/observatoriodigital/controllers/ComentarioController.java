package com.tlachco.observatoriodigital.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;

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
