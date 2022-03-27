package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tlachco.observatoriodigital.domains.Articulo;
import com.tlachco.observatoriodigital.domains.CategoriaArticulo;
import com.tlachco.observatoriodigital.services.ICategoriaArticuloService;

@Controller
@RequestMapping("/post")
public class PostCrontroller {
	
	@Autowired
	public ICategoriaArticuloService categoriaS;

	@RequestMapping("/creacion")
	public String creacion_post(Model model) {
		
		Articulo articulo = new Articulo();
		
		List<CategoriaArticulo> categorias = null;
		
		try {
			categorias = categoriaS.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("articulo", articulo);	
		
		return "crearPost";
	}
	
	@RequestMapping("/validar-creacion")
	public ModelAndView validar_post() {
		ModelAndView mav = new ModelAndView();
		
		
		
		return mav;
	}
}
