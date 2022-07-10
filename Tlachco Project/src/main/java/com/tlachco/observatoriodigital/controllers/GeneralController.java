package com.tlachco.observatoriodigital.controllers;

import java.util.List;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.dto.ArchivoDTO;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.services.IArchivoService;
import com.tlachco.observatoriodigital.services.IPublicacionService;
import com.tlachco.observatoriodigital.services.IUsuarioService;

@Controller
public class GeneralController {
	
	@Autowired
	public IPublicacionService publicacionService;
	
	@Autowired
	public IUsuarioService usuarioService;
	
	@Autowired
	public IArchivoService archivoService;

	
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
	
	@RequestMapping("/perfil/{username}")
	public String perfilDeUsuario(@PathVariable("username") String username, Model model) {
		
		Usuario usuario = usuarioService.findOne(username);
		
		List<PublicacionesDTO> listaResultados = null;
		List<PublicacionesDTO> listaPublicacionesReview = null;
		List<ArchivoDTO> findMyInfografia = null;
		List<ArchivoDTO> findStudentInfografia = null;
		
		try {
			listaResultados = publicacionService.findAllPublicacionesByPropietario(username);
			listaPublicacionesReview = publicacionService.findReviewPublicacionesByProfesor(username);
			findMyInfografia = archivoService.findMyInfografia(username);
			findStudentInfografia = archivoService.findStudentInfografia(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("listaResultados", listaResultados);
		model.addAttribute("listaPublicacionesReview", listaPublicacionesReview);
		model.addAttribute("findMyInfografia", findMyInfografia);
		model.addAttribute("findStudentInfografia", findStudentInfografia);
		model.addAttribute("usuario",usuario);
		return "perfil";
				
	}
	
	@RequestMapping("/perfil/{username}/pass")
	public String editarPass(@PathVariable("username") String username, Model model) {
		
		Usuario usuario = usuarioService.findOne(username);
		
		model.addAttribute("usuario",usuario);
		return "editarPass";
				
	}
	
	@RequestMapping("/perfil/{username}/validar-pass")
	public String validarPass(@Valid @ModelAttribute Usuario usuario, BindingResult result, @PathVariable(value="username") String username, Principal principal, @RequestParam("password") String password, @RequestParam("valid_password") String valid_password,  Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("usuario",usuario);
			return "editarPass";
		}
		else if(!password.equals(valid_password)){
			model.addAttribute("usuario",usuario);
			return "editarPass";
			
		}
		else {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(usuario.getPassword());
			
			usuario.setPassword(encodedPassword);
			
			try {
				usuarioService.save(usuario);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(usuario.getUsuario().equals(principal.getName())) {
				System.out.println(usuario.getUsuario());
				System.out.println("   ");
				System.out.println(principal.getName());
				model.addAttribute("umensaje","La contraseña fue cambiada con éxito.");
				return "perfil";
			}else {
				List<Usuario> usuarios = null;
				
				try {
					usuarios = usuarioService.findAll();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				model.addAttribute("umensaje","Contraseña del usuario actualizado con éxito.");
				model.addAttribute("users", usuarios);
				return "listaUsuarios";
			}
			
		}
				
	}
	
}
