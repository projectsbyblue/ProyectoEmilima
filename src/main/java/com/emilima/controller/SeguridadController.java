package com.emilima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emilima.model.Usuario;
import com.emilima.repository.IUsuarioRepository;

@Controller
public class SeguridadController {
	
	@Autowired
	IUsuarioRepository usuarioRepo;
	
	@GetMapping("/")
	public String login() {
		return "escritorio";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/acceso")
	public String acceso(@ModelAttribute Usuario usuario, Model model) {
		
		if(usuarioRepo.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave()) == null) {
			model.addAttribute("mensaje","Usuario o Clave incorrectos");
			return "login";			
		}
		
		// Enviar datos a session
		
		return "escritorio";
	}
}
