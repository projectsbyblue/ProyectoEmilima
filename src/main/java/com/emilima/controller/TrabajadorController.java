package com.emilima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		System.out.println(repoTrabajador.findLastTrabajador());
		trabajador.setIdTrabajador(codigoCorrelativo(repoTrabajador.findLastTrabajador()));
		
		try {
			System.out.println(trabajador);
			repoTrabajador.save(trabajador);
			model.addAttribute("mensajeRegistro", "Trabajador registrado");
		} catch (Exception e) {
			model.addAttribute("mensajeRegistro", "Error al registrar trabajador");
			e.printStackTrace();
		}
		return "registrar-trabajador";
	}
	
	@GetMapping("/trabajador/actualizar")
	public String actualizarTrabajador(Model model) {
		model.addAttribute("trabajador", new Trabajador());
		return "actualizar-trabajador";
	}
	
	@PostMapping("/trabajador/actualizar")
	public String actualizarTrabajador(@RequestParam(name="opcion") String opcion,
			@ModelAttribute Trabajador trabajador,
			Model model) {
		
		if(opcion.equals("b")) {
			trabajador = repoTrabajador.findByDni(trabajador.getDni());
			if(trabajador == null) {
				model.addAttribute("mensajeTrabajador", "Trabajador no encontrado");
			} else {
				model.addAttribute("trabajador", trabajador);
			}
			
			return "actualizar-trabajador";				
		}
		
		try {
			trabajador = repoTrabajador.findByDni(trabajador.getDni());
			repoTrabajador.save(trabajador);
			model.addAttribute("mensajeRegistro", "Trabajador actualizado.");
		} catch (Exception e) {
			model.addAttribute("mensajeRegistro", "Error al actualizar Trabajador." + e.getMessage());
			e.printStackTrace();
		}
		return "actualizar-trabajador";
		
	}
	
	@GetMapping("/trabajador/listar")
	public String listadoTrabajadores(Model model) {		
		model.addAttribute("lstTrabajadores", repoTrabajador.findAll());
		return "listar-trabajadores";
			
	}
		
	private String codigoCorrelativo(String idTrabajador) {
		String codigoCorrelativo = "";
		char[] codigo= idTrabajador.toCharArray();
		int contador = 0;
		int nroTrabajador = 0;
		for(char a : codigo){
			contador++;
			if( a != 'T'  && a != '0') {
				nroTrabajador = Integer.parseInt(idTrabajador.substring(contador - 1, 5)) + 1;
				break;
			} else if (contador == 5) {
				return codigoCorrelativo = "T0001";
			}
		}
		if(nroTrabajador < 10) {
			codigoCorrelativo = "T000" + nroTrabajador ;
		}else if (nroTrabajador < 100) {
			codigoCorrelativo = "T00" + nroTrabajador;
		} else if (nroTrabajador < 1000) {
			codigoCorrelativo = "T0" + nroTrabajador;
		}else {
			codigoCorrelativo = "T" + nroTrabajador;
		}
		
		return codigoCorrelativo;
	}
}
