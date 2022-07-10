package com.tlachco.observatoriodigital.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.dto.ComentarioDTO;
import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;
import com.tlachco.observatoriodigital.services.IVideoService;

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
	public String creacion_post(@RequestParam String categoria, Principal principal, Model model) {

		// List<CategoriaPublicacion> categorias = null;
		Integer id_categoria = null;
		String id_usuario = principal.getName();
		Usuario user = usuarioService.findOne(id_usuario);

		Publicacion publicacion = new Publicacion();
		List<Usuario> teachers = null;
		
		if(user.getRol().getId_rol() == 3) {
			try {
				teachers = usuarioService.findTeachers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Archivo archivo = new Archivo();

		model.addAttribute("archivo", archivo);
		model.addAttribute("teachers", teachers);
		model.addAttribute("categoria", categoria);
		// model.addAttribute("categorias", categorias);
		model.addAttribute("id_categoria", id_categoria);
		model.addAttribute("publicacion", publicacion);

		return "crearPost";
	}

	@RequestMapping("/articuloDetail/{idPublicacion}")
	public String perfilDeUsuario(@PathVariable("idPublicacion") String idPublicacion, Principal principal,
			Model model) {
		String id_usuario;
		if (principal != null) {
			id_usuario = principal.getName();
			if (id_usuario != null) {
				Comentario comentario = new Comentario();
				model.addAttribute("comentario", comentario);
			}
		}
		Publicacion publicacion = publicacionService.findOne(Integer.parseInt(idPublicacion));
		List<ComentarioDTO> listaComentarios = null;

		try {
			listaComentarios = comentarioService.findCommentByPublication(Integer.parseInt(idPublicacion));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listaComentarios", listaComentarios);
		model.addAttribute("publicacion", publicacion);
		return "articuloDetail";
	}

	@RequestMapping("/validar-creacion")
	public String validar_post(@Valid @ModelAttribute Publicacion publicacion, BindingResult result,
			Principal principal, @RequestParam String categoria, @RequestParam String teacherSelect, HttpServletRequest request, @ModelAttribute Archivo archivo, BindingResult result2,
			@RequestParam(defaultValue = "false") Boolean concentimiento, @RequestParam(value = "file") MultipartFile file, Model model) {

		String estado = null;
		System.out.println(concentimiento);
		if(file.isEmpty() == false && concentimiento == false) {
			String id_usuario = principal.getName();
			Usuario user = usuarioService.findOne(id_usuario);
			List<Usuario> teachers = null;
			
			if(user.getRol().getId_rol() == 3) {
				try {
					teachers = usuarioService.findTeachers();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("archivo", archivo);
			model.addAttribute("teachers", teachers);
			model.addAttribute("categoria", categoria);
			model.addAttribute("publicacion", publicacion);

			return "crearPost";
		}
		if(request.isUserInRole("ROLE_STUDENT")) {
			if(teacherSelect.equals("0")) {
				String id_usuario = principal.getName();
				Usuario user = usuarioService.findOne(id_usuario);
				List<Usuario> teachers = null;
				
				if(user.getRol().getId_rol() == 3) {
					try {
						teachers = usuarioService.findTeachers();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				model.addAttribute("archivo", archivo);
				model.addAttribute("teachers", teachers);
				model.addAttribute("categoria", categoria);
				model.addAttribute("publicacion", publicacion);

				return "crearPost";
			}
		}
		if(result.hasErrors()) {
			String id_usuario = principal.getName();
			Usuario user = usuarioService.findOne(id_usuario);
			List<Usuario> teachers = null;
			
			if(user.getRol().getId_rol() == 3) {
				try {
					teachers = usuarioService.findTeachers();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("archivo", archivo);
			model.addAttribute("teachers", teachers);
			model.addAttribute("categoria", categoria);
			model.addAttribute("publicacion", publicacion);

			return "crearPost";
		} else {
			if(file.isEmpty() == false) {				
				archivo.setCategoriaPublicacion(categoriaService.findOne(5));
				String id_archivo = archivoService.save2(archivo, file);
				Archivo archivoaux = archivoService.findOne(id_archivo);
				publicacion.setArchivo(archivoaux);
			}

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
			
			System.out.println("SELECT VALOR PARA " + teacherSelect);
			if(teacherSelect != null) {
				publicacion.setProfesor(usuarioService.findOne(teacherSelect));
			}
			publicacion.setFecha_publicacion(new java.util.Date());
			publicacion.setEstado(estado);
			publicacion.setUsuario(propietario);
			publicacion.setCategoriaPublicacion(id_categoria);

			try {
				publicacionService.save(publicacion);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/post/articuloDetail/" + publicacion.getId_publicacion();
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

	@RequestMapping("/editar/{id_publicacion}")
	public String editarPublicacion(@PathVariable("id_publicacion") String id_publicacion,
			@RequestParam("categoria") Integer categoria, Model model) {
		if (id_publicacion != null) {
			Publicacion publicacion = publicacionService.findOne(Integer.parseInt(id_publicacion));
			String miCategoria = null;

			if (categoria == 1) {
				miCategoria = "Noticia";
			} else if (categoria == 2) {
				miCategoria = "Articulo";
			}

			System.out.println(categoria);
			model.addAttribute("miCategoria", miCategoria);
			model.addAttribute("publicacion", publicacion);
		}
		return "editarPost";
	}

	@RequestMapping("/validar-edicion")
	public String validarEdicionPublicacion(@Valid @ModelAttribute Publicacion publicacion, BindingResult result,
			@RequestParam("id_publicacion") String id_publicacion, @RequestParam("categoria") String categoria,
			Model model) {
		if (result.hasErrors() || id_publicacion == null) {

			System.out.println(categoria);

			model.addAttribute("categoria", categoria);
			model.addAttribute("publicacion", publicacion);

			return "editarPost";
		} else {
			Publicacion publicacionAux = publicacionService.findOne(Integer.parseInt(id_publicacion));
			if(publicacionAux.getEstado().equals("Reviewed")) {
				publicacion.setEstado("Review");
			}else {
				publicacion.setEstado(publicacionAux.getEstado());
			}
			publicacion.setFecha_publicacion(publicacionAux.getFecha_publicacion());
			publicacion.setCategoriaPublicacion(publicacionAux.getCategoriaPublicacion());
			publicacion.setUsuario(publicacionAux.getUsuario());
			publicacion.setArchivo(publicacionAux.getArchivo());
			publicacion.setProfesor(publicacionAux.getProfesor());

			try {
				publicacionService.save(publicacion);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/";
		}
	}

}
