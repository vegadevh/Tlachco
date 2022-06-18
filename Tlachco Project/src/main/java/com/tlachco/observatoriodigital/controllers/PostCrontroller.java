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
import com.tlachco.observatoriodigital.dto.ComentarioDTO;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IComentarioService;
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
	
	@Autowired
	public IComentarioService comentarioService;

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
	
	@RequestMapping("/articuloDetail/{idPublicacion}")
	public String perfilDeUsuario(@PathVariable("idPublicacion") String idPublicacion, Principal principal, Model model) {
		String id_usuario;
		if(principal != null) {			
			id_usuario = principal.getName();
			if(id_usuario != null) {
				Comentario comentario = new Comentario();
				model.addAttribute("comentario",comentario);
			}
		}
		Publicacion publicacion = publicacionService.findOne(Integer.parseInt(idPublicacion));
		List<ComentarioDTO> listaComentarios = null;
		
		try {
			listaComentarios = comentarioService.findCommentByPublication(Integer.parseInt(idPublicacion));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listaComentarios",listaComentarios);
		model.addAttribute("publicacion",publicacion);
		return "articuloDetail";
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
	
	@RequestMapping("/editar/{id_publicacion}")
	public String editarPublicacion(@PathVariable("id_publicacion") String id_publicacion, @RequestParam("categoria") Integer categoria, Model model) {
		if(id_publicacion != null) {
			Publicacion publicacion = publicacionService.findOne(Integer.parseInt(id_publicacion));
			String miCategoria = null;
			
			if(categoria == 1) {
				miCategoria = "Noticia";
			}else if(categoria == 2) {
				miCategoria = "Articulo";
			}
			
			System.out.println(categoria);
			model.addAttribute("miCategoria", miCategoria);
			model.addAttribute("publicacion", publicacion);
		}
		return "editarPost";
	}
	
	@RequestMapping("/validar-edicion")
	public String validarEdicionPublicacion(@Valid @ModelAttribute Publicacion publicacion, @RequestParam("id_publicacion") String id_publicacion, @RequestParam("categoria") String categoria, Model model) {
		if(id_publicacion == null) {
			
			System.out.println(categoria);
			
			model.addAttribute("categoria", categoria);
			model.addAttribute("publicacion", publicacion);
			
			return "editarPost";
		} else {
			Publicacion publicacionAux = publicacionService.findOne(Integer.parseInt(id_publicacion));
			publicacion.setFecha_publicacion(publicacionAux.getFecha_publicacion());
			publicacion.setEstado(publicacionAux.getEstado());
			publicacion.setUsuario(publicacionAux.getUsuario());
			publicacion.setCategoriaPublicacion(publicacionAux.getCategoriaPublicacion());

			try {
				publicacionService.save(publicacion);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/";
		}
	}
	
}
