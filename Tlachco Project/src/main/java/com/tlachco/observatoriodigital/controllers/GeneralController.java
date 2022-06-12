package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.services.IPublicacionService;

@Controller
@RequestMapping("/general")
public class GeneralController {
	
	@Autowired
	public IPublicacionService publicacionService;

	
	@RequestMapping("/busquedaPublicaciones")
	public String busquedaPublicaciones(Model model, String titulo) {
		
		List<PublicacionesDTO> listaResultados = null;
		
	
		try {
			titulo = titulo.toLowerCase();
			listaResultados = publicacionService.findByKeyword(titulo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listaResultados", listaResultados);

		return "busqueda";
	}
}
