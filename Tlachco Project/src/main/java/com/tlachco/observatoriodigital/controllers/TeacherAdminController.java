package com.tlachco.observatoriodigital.controllers;

import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IVideoService;

@Controller
@RequestMapping("/post")
public class TeacherAdminController {

	
	@Autowired
	public IPublicacionService publicacionService;
	
	@Autowired
	public IComentarioService comentarioService;

	@Autowired
	public IVideoService videoService;
	
	@RequestMapping("/eliminar/publicacion/{id_publicacion}")
	public String eliminarPublicacion(@RequestParam(value="id_publicacion") String id_publicacion) {
		if(id_publicacion != null) {
			comentarioService.eliminarComentario(id_publicacion);
			publicacionService.deletePublicacion(Integer.parseInt(id_publicacion));
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/eliminar/comentario/{id_comentario}")
	public String eliminarComentario(@PathVariable(value="id_comentario") String id_comentario, @RequestParam(value="id_publicacion") String id_publicacion, Model model) {
		
		if(id_comentario != null) {
			comentarioService.delete(Integer.parseInt(id_comentario));
			
		}
		String pub = id_publicacion;
		model.addAttribute("id_publicacion", pub);
		return "redirect:/post/articuloDetail/"+pub;
	}
	
	@RequestMapping("/eliminar/video/{id_video}")
	public String eliminarVideo(@RequestParam(value="id_video") String id_video) {
		
		if(id_video != null) {
			videoService.delete(Integer.parseInt(id_video));
			
		}
		return "redirect:/articulos";
	}
	
}
