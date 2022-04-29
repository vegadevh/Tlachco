package com.tlachco.observatoriodigital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/noticias")
	public String noticias(Model model) {

		return "noticias";
	}
	
	@RequestMapping("/articulos")
	public String articulos(Model model) {

		return "articulos";
	}
	
	@RequestMapping("/videos")
	public String videos(Model model) {

		return "videos";
	}
	
	@RequestMapping("/infografias")
	public String infografias(Model model) {

		return "infografias";
	}
	
	@RequestMapping("/sobre-nosotros")
	public String sobreNosotros(Model model) {

		return "sobre-nosotros";
	}
	
}
