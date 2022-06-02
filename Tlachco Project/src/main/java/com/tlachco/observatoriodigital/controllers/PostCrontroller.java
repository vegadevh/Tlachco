package com.tlachco.observatoriodigital.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;
import com.tlachco.observatoriodigital.services.IVideoService;
import com.tlachco.observatoriodigital.services.VideoServiceImpl;

@Controller
@RequestMapping("/post")
public class PostCrontroller {

	@Autowired
	public ICategoriaPublicacionService categoriaService;

	@Autowired
	public IArchivoService archivoService;

	@Autowired
	public IUsuarioService usuarioService;

	@Autowired
	public IPublicacionService publicacionService;
	
	@Autowired
	public IVideoService videoService;

	@RequestMapping("/creacion")
	public String creacion_post(@RequestParam String categoria, Model model) {

		// List<CategoriaPublicacion> categorias = null;
		Integer id_categoria = null;

		Publicacion publicacion = new Publicacion();

		model.addAttribute("categoria", categoria);
		// model.addAttribute("categorias", categorias);
		model.addAttribute("id_categoria", id_categoria);
		model.addAttribute("publicacion", publicacion);

		return "crearPost";
	}

	@RequestMapping("/validar-creacion")
	public String validar_post(@Valid @ModelAttribute Publicacion publicacion, BindingResult result,
			Principal principal, @RequestParam String categoria, HttpServletRequest request, Model model) {

		String estado = null;

		if (result.hasErrors()) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("publicacion", publicacion);

			return "crearPost";
		} else {

			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_TEACHER")) {
				estado = "Public";
			} else {
				estado = "Review";
			}
			
			String id_usuario = principal.getName();
			
			CategoriaPublicacion id_categoria = new CategoriaPublicacion();
			id_categoria = categoriaService.findByCategoria(categoria);

			Usuario propietario = new Usuario();
			propietario = usuarioService.findOne(id_usuario);

			publicacion.setFecha_publicacion(new java.util.Date());
			publicacion.setEstado(estado);
			publicacion.setUsuario(propietario);
			publicacion.setCategoriaPublicacion(id_categoria);

			try {
				publicacionService.save(publicacion);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "index";
		}
	}
	
	@RequestMapping("/validar-video")
	public String validar_video(@Valid @ModelAttribute Video video, BindingResult result,
			Principal principal, @RequestParam String categoria, HttpServletRequest request, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("video", video);

			return "videos";
		} else {
			
			CategoriaPublicacion id_categoria = new CategoriaPublicacion();
			id_categoria = categoriaService.findByCategoria(categoria);

			video.setCategoriaPublicacion(id_categoria);

			try {
				videoService.save(video);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			List<Video> listaVideos = null;
			Video auxvideo = new Video();
			
			try {
				listaVideos = videoService.findAllByIdOrderByDesc();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("categoria", categoria);
			model.addAttribute("auxvideo", auxvideo);
			model.addAttribute("listaVideos", listaVideos);

			return "redirect:/videos";
		}
	}

	@RequestMapping("/subir")
	public String subir(Model model) {
		Archivo archivo = new Archivo();

		model.addAttribute("archivo", archivo);

		return "subirArchivo";
	}

	// TEST - VALIDACIONES FALTANTES
	@PostMapping("/validacion-subir")
	public String validar_subir(@ModelAttribute Archivo archivo, BindingResult result,
			@RequestParam(value = "file") MultipartFile file, Model model) throws IOException {

		archivoService.save(archivo, file);

		return "redirect:/";
	}

	@RequestMapping("/archivo/{id_archivo}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String id_archivo) {
		Archivo archivo = archivoService.findOne(id_archivo);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(archivo.getTipo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + archivo.getNombre() + "\"")
				.body(new ByteArrayResource(archivo.getContenido()));
	}
}
