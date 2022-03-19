package com.tlachco.observatoriodigital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/usuario_registro")
	public ModelAndView  usuario_registro() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		return mav;
	}

}
