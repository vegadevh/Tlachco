package com.tlachco.observatoriodigital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tlachco.observatoriodigital.domains.Rol;
import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.services.IRolService;
import com.tlachco.observatoriodigital.services.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolService rolService;
	
	@RequestMapping("/usuario_registro")
	public String creacion_usuario(Model model) {
		Usuario usuario = new Usuario();

		List <Rol> roles = null;
		
		try {
			roles = rolService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		usuario.setEnabled_u(true);
		model.addAttribute("roles", roles);
		model.addAttribute("usuario", usuario);
		
		return "crearUsuario";
	}

	@RequestMapping("validar_registro")
	public String validar_registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model,
			@RequestParam(value="rol.id_rol") String rolSelect,
			@RequestParam(value="usuario") String id_usuario) {
		
		Usuario usuarioAux = usuarioService.findOne(id_usuario);
		
		if (result.hasErrors()) {
			List <Rol> roles = null;
			
			try {
				roles = rolService.findAll();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			usuario.setEnabled_u(true);
			model.addAttribute("roles", roles);
			model.addAttribute("usuario", usuario);
			
			return "crearUsuario";
		}else if (usuarioAux != null){
			List <Rol> roles = null;
			
			try {
				
				roles = rolService.findAll();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("roles", roles);
			model.addAttribute("iderror", "El nombre de usuario ya esta en uso.");
			model.addAttribute("usuario", usuario);
			return "crearUsuario";
		} else if(rolSelect.equals("0")) {
			List <Rol> roles = null;
			
			try {
				
				roles = rolService.findAll();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("roles", roles);
			model.addAttribute("alertRol","Debe seleccionar un rol para el usuario.");
			model.addAttribute("usuario", usuario);
			return "crearUsuario";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(usuario.getPassword());
			
			usuario.setPassword(encodedPassword);
			
			try {
				usuarioService.save(usuario);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			model.addAttribute("umensaje","El usuario ha sido creado con éxito.");
			return "index";
		}
	}
	
	
	@RequestMapping("/lista_usuarios")
	public String listaUsuarios(Model model) {
		
		List<Usuario> usuarios = null;
		
		try {
			usuarios = usuarioService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("users", usuarios);
		return "listaUsuarios";
	}
	
}
