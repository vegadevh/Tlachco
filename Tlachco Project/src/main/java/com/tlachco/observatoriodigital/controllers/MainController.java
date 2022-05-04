package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.services.IArchivoService;

@Controller
public class MainController {
	
	@Autowired
	private IArchivoService archivoService;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Archivo> listaArchivos = null;
		try {
			listaArchivos = archivoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute(listaArchivos);

		return "index";
	}
	
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
