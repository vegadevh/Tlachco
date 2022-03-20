package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView creacion_post() {
		ModelAndView mav = new ModelAndView();
		
		Articulo articulo = new Articulo();
		
		List<CategoriaArticulo> categorias = null;
		
		try {
			categorias = categoriaS.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject(categorias);
		
		mav.addObject(articulo);
		
		mav.setViewName("crearPost");
		
		return mav;
	}
	
	@RequestMapping("/validarCreacion")
	public ModelAndView validar_post() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("crearPost");
		
		return mav;
	}
}
