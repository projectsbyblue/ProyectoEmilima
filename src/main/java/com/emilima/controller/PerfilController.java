package com.emilima.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.emilima.model.Perfil;
import com.emilima.model.Usuario;
import com.emilima.repository.IPerfilRepository;
import com.emilima.repository.IUsuarioRepository;

@Controller
public class PerfilController {
	
	@Autowired
	private IPerfilRepository perfilRepo;
	
	@Autowired 
	private IUsuarioRepository usuarioRepo;
	
	@GetMapping("/perfil/cargar")
	public String cargarForm(Model model) {
		
		model.addAttribute("perfil", new Perfil());
		model.addAttribute("usuario", new Usuario());
		return "actualizar-perfil";
	}
	
	@PostMapping("/perfil/actualizar")
	public String actualizarPerfil(@ModelAttribute Perfil perfil,
			@RequestParam("foto") MultipartFile foto,
			@ModelAttribute Usuario usuario,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "password2") String password2,
			Model model) {		
		
		try {
			if(!foto.isEmpty()) {
				Path ruta = null;
				String rutaFoto = "";
				try {
					byte[] bytes = foto.getBytes();
					
					if(perfil.getFoto().isEmpty()) {
						rutaFoto = "/fotos/" + perfil.getIdPersonal() + foto.getOriginalFilename();
					} else {
						rutaFoto = perfil.getFoto();
					}
					ruta = Paths.get(ResourceUtils.getFile("classpath:static").getPath() + rutaFoto);
					
					Files.write(ruta, bytes);
					perfil.setFoto(rutaFoto);
					perfilRepo.save(perfil);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if(password.equals(password2)) {
				usuarioRepo.save(usuario);				
			}
			model.addAttribute("mensajeRegistro", "Perfil actualizado.");
		} catch (Exception e) {
			model.addAttribute("mensajeRegistro", "Error al actualizar Perfil. " + e.getMessage());
			e.printStackTrace();
		}
		
		return "actualizar-perfil";
		
	}
}
