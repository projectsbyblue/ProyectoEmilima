package com.emilima.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emilima.model.Perfil;
import com.emilima.model.Usuario;
import com.emilima.repository.IPerfilRepository;
import com.emilima.repository.IUsuarioRepository;

@Controller
public class SeguridadController {
	
	@Autowired
	IUsuarioRepository usuarioRepo;
	
	@Autowired
	IPerfilRepository perfilRepo;
	
	@GetMapping("/")
	public String login(HttpSession session, Model model) {
		model.addAttribute("usuario", new Usuario());
		if(session.getAttribute("usuario") == null) {
			return "login";
		}
		
		model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
		model.addAttribute("perfil", (Perfil) session.getAttribute("perfil"));
		return "escritorio";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/acceso")
	public String acceso(HttpSession session,@ModelAttribute Usuario usuario, Model model) {
		
		if(usuarioRepo.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave()) == null) {
			model.addAttribute("mensaje","Usuario o Clave incorrectos");
			return "login";			
		}		
		
		session.setAttribute("usuario", usuarioRepo.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave()));
		session.setAttribute("perfil", perfilRepo.findByUsuario(usuario.getUsuario()));
		
		return "escritorio";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("usuario", new Usuario());
		session.setAttribute("usuario", null);
		session.setAttribute("perfil", null);
		
		return "login";
	}
}
