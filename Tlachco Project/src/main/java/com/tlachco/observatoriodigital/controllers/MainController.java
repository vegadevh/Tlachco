package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IVideoService;

@Controller
public class MainController {

	@Autowired
	private IArchivoService archivoService;
	
	@Autowired
	public IVideoService videoService;
	
	@Autowired
	public IPublicacionService publicacionService;

	@RequestMapping("/")
	public String index(Model model) {
		
		List<PublicacionesDTO> listaNoticias = null;
		List<PublicacionesDTO> listaArticulos = null;
		List<Video> listaVideos = null;
		
		try {
			listaNoticias = publicacionService.findTopThreePublicaciones(1);
			listaArticulos = publicacionService.findTopThreePublicaciones(2);
			listaVideos = videoService.findTopVideos();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("listaNoticias", listaNoticias);
		model.addAttribute("listaArticulos", listaArticulos);
		model.addAttribute("listaVideos", listaVideos);
		
		return "index";
	}

	@RequestMapping("/noticias")
	public String noticias(Model model) {
		
		List<PublicacionesDTO> listaResultados = null;
		
		try {
			listaResultados = publicacionService.findAllNoticias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listaResultados", listaResultados);

		return "noticias";
	}

	@RequestMapping("/articulos")
	public String articulos(Model model) {
		
		List<PublicacionesDTO> listaResultados = null;
		
		try {
			listaResultados = publicacionService.findAllArticulos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listaResultados", listaResultados);

		return "articulos";
	}

	@RequestMapping("/videos")
	public String videos(Model model) {
		
		Video video = new Video();
		List<Video> listaVideos = null;
		String categoria = "Video";
		
		try {
			listaVideos = videoService.findAllByIdOrderByDesc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("categoria", categoria);
		model.addAttribute("video", video);
		model.addAttribute("listaVideos", listaVideos);

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
	
	@RequestMapping("/403")
	public String Error(Model model) {

		return "403";
	}

}
