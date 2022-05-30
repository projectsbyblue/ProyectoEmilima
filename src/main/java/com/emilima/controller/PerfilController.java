package com.emilima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emilima.model.Perfil;
import com.emilima.repository.IPerfilRepository;

@Controller
public class PerfilController {
	
	@Autowired
	private IPerfilRepository repoPerfil;
	
	@GetMapping("/perfil/cargar")
	public String cargarForm(Model model) {
		
		model.addAttribute("perfil", new Perfil());
		return "actualizar-perfil";
	}
	
	@PostMapping("/perfil/actualizar")
	public String actualizarPerfil(@ModelAttribute Perfil perfil, Model model) {		
		try {
			repoPerfil.save(perfil);
			model.addAttribute("success", "Perfil actualizado.");
		} catch (Exception e) {
			model.addAttribute("error", "Error al actualizar Perfil. " + e.getMessage());
			e.printStackTrace();
		}
		return "actualizar-perfil";
		
	}
	
	@PostMapping("/perfil/buscar")
	public String buscarTrabajador(@ModelAttribute Perfil p, Model model) {	
		model.addAttribute("perfil",repoPerfil.findByUsuario(p.getUsuario()));	
		return "actualizar-perfil";
		
	}
}
