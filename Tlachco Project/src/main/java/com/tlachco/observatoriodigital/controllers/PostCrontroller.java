package com.tlachco.observatoriodigital.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;

@Controller
@RequestMapping("/post")
public class PostCrontroller {

	@Autowired
	public ICategoriaPublicacionService categoriaService;

	@Autowired
	public IArchivoService archivoService;

	@RequestMapping("/creacion")
	public String creacion_post(Model model) {

		Publicacion publicacion = new Publicacion();

		// Cambios
		List<CategoriaPublicacion> categorias = null;

		try {
			categorias = categoriaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Cambios

		model.addAttribute("categorias", categorias);
		model.addAttribute("publicacion", publicacion);

		return "crearPost";
	}

	@RequestMapping("/subir")
	public String subir(Model model) {
		Archivo archivo = new Archivo();

		model.addAttribute("archivo", archivo);

		return "subirArchivo";
	}

	@PostMapping("/validacion-subir")
	public String validar_subir(@ModelAttribute Archivo archivo, BindingResult result,
			@RequestParam(value = "file") MultipartFile file, Model model) throws IOException {

		archivoService.save(archivo, file);

		return "redirect:/";
	}

	// MultipartFile file
	// archivoService.save(archivo, file);

	@RequestMapping("/validar-creacion")
	public ModelAndView validar_post() {
		ModelAndView mav = new ModelAndView();

		return mav;
	}
}
