package com.tlachco.observatoriodigital.controllers;

import java.io.IOException;

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

import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.ICategoriaPublicacionService;
import com.tlachco.observatoriodigital.services.IComentarioService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IVideoService;

@Controller
@RequestMapping("/post")
public class TeacherAdminController {

	@Autowired
	public IPublicacionService publicacionService;

	@Autowired
	public IComentarioService comentarioService;

	@Autowired
	public IVideoService videoService;
	
	@Autowired
	public IArchivoService archivoService;
	
	@Autowired
	public ICategoriaPublicacionService categoriaService;

	@RequestMapping("/eliminar/publicacion/{id_publicacion}")
	public String eliminarPublicacion(@RequestParam(value = "id_publicacion") String id_publicacion) {
		if (id_publicacion != null) {
			comentarioService.eliminarComentario(id_publicacion);
			publicacionService.deletePublicacion(Integer.parseInt(id_publicacion));
		}

		return "redirect:/";
	}
	
	@RequestMapping("/eliminar/archivo/{id_archivo}")
	public String eliminarArchivo(@PathVariable(value = "id_categoria") String id_categoria,
			@RequestParam(value = "id_archivo") String id_archivo) {
		if (id_archivo != null) {
			System.out.println("HOLAAAAAAAA"+id_archivo);
			System.out.println("HOLAAAAAAAA"+id_categoria);
			//comentarioService.eliminarComentario(id_publicacion);
			//publicacionService.deletePublicacion(Integer.parseInt(id_publicacion));
		}

		return "redirect:/";
	}
		

	@RequestMapping("/eliminar/comentario/{id_comentario}")
	public String eliminarComentario(@PathVariable(value = "id_comentario") String id_comentario,
			@RequestParam(value = "id_publicacion") String id_publicacion, Model model) {

		if (id_comentario != null) {
			comentarioService.delete(Integer.parseInt(id_comentario));

		}
		String pub = id_publicacion;
		model.addAttribute("id_publicacion", pub);
		return "redirect:/post/articuloDetail/" + pub;
	}

	@RequestMapping("/eliminar/video/{id_video}")
	public String eliminarVideo(@RequestParam(value = "id_video") String id_video) {

		if (id_video != null) {
			videoService.delete(Integer.parseInt(id_video));

		}
		return "redirect:/videos";
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

		archivo.setCategoriaPublicacion(categoriaService.findOne(5));
		archivoService.save(archivo, file);

		return "redirect:/";
	}
	
	@PostMapping("/validacion-infografia")
	public String validar_infografia(@ModelAttribute Archivo archivo, BindingResult result,
			@RequestParam(value = "file") MultipartFile file, @RequestParam String id_categoria, Model model) throws IOException {
		
		archivo.setCategoriaPublicacion(categoriaService.findOne(Integer.parseInt(id_categoria)));
		archivoService.save(archivo, file);

		return "redirect:/infografias";
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
