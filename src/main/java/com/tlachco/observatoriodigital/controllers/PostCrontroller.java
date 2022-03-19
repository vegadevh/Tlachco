package com.tlachco.observatoriodigital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post")
public class PostCrontroller {

	@RequestMapping("/creacion")
	public ModelAndView creacion_post() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("crearPost");
		
		return mav;
	}
}
