package com.emilima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emilima.model.Trabajador;
import com.emilima.repository.ITrabajadorRepository;

@Controller
public class TrabajadorController {

	@Autowired
	private ITrabajadorRepository repoTrabajador;
	
	@GetMapping("/trabajador/cargar")
	public String cargarForm(Model model) {
		
		model.addAttribute("trabajador", new Trabajador());
		return "registrar-trabajador";
	}
	
	@PostMapping("/trabajador/registrar")
	public String registrarTrabajador(@ModelAttribute Trabajador trabajador,
			Model model) {
		trabajador.setIdTrabajador(codigoCorrelativo(repoTrabajador.findLastTrabajador()));
		
		try {
			repoTrabajador.save(trabajador);
			model.addAttribute("success", "Trabajador registrado");
		} catch (Exception e) {
			model.addAttribute("error", "Error al registrar trabajador");
			e.printStackTrace();
		}
		return "registrar-trabajador";
	}
	
	@GetMapping("/trabajador/actualizar")
	public String actualizarTrabajador(Model model) {
		
		return "actualizar-trabajador";
	}
	
	@PostMapping("/trabajador/actualiza")
	public String actualizarTrabajador(@ModelAttribute Trabajador trabajador,
			Model model) {
		System.out.println(trabajador);
		
		try {
			repoTrabajador.save(trabajador);
			model.addAttribute("success", "Trabajador actualizado.");
		} catch (Exception e) {
			model.addAttribute("error", "Error al actualizar Trabajador." + e.getMessage());
			e.printStackTrace();
		}
		return "actualizar-trabajador";
		
	}
	
	
	
	@PostMapping("/trabajador/buscar")
	public String buscarTrabajador(@ModelAttribute Trabajador tr, Model model) {	
		model.addAttribute("trabajador",repoTrabajador.findById(tr.getIdTrabajador()));	
		return "actualizar-trabajador";
		
	}
	
	@GetMapping("/trabajador/listar")
	public String listadoTrabajadores(Model model) {		
		model.addAttribute("lstTrabajadores", repoTrabajador.findAll());
		return "listar-trabajadores";
			
	}
		
	private String codigoCorrelativo(String idUltimoTrabajador) {
		String codigoCorrelativo = "";
		char[] codigo= idUltimoTrabajador.toCharArray();
		int contador = 0;
		int nroTrabajador = 0;
		for(char a : codigo){
			contador++;
			if( a != 'T' && a != '0') {
				nroTrabajador = Integer.parseInt(idUltimoTrabajador.substring(contador - 1, 3)) + 1;
				break;
			}else if (contador == 3) {
				return codigoCorrelativo = "T0001";
			}
		}
		
		if(nroTrabajador < 10) {
			codigoCorrelativo = "T0" + nroTrabajador;
		}else {
			codigoCorrelativo = "T" + nroTrabajador;
		}		
		return codigoCorrelativo;
	}
}
