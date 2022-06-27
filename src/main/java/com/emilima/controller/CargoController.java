package com.emilima.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilima.model.Cargo;
import com.emilima.repository.ICargoRepository;

@Controller
public class CargoController {

	@Autowired
	private ICargoRepository repoCargo;
	
	
	@GetMapping("/cargo/cargar")
	public String cargarForm(Model model) {
		
		model.addAttribute("cargo", new Cargo());
		
		return "registrar-cargo";
		
	}
	
	@PostMapping("/cargo/registrar")
	public String registrarCargo(@ModelAttribute Cargo cargo,
			Model model) {
		System.out.println(cargo);
		
		cargo.setIdCargo(codigoCorrelativo(repoCargo.findLastCargo()));
		
		try {
			repoCargo.save(cargo);
			model.addAttribute("mensajeRegistro", "Cargo registrado.");
		} catch (Exception e) {
			model.addAttribute("mensajeRegistro", "Error al registrar cargo.");
			e.printStackTrace();
		}
		return "registrar-cargo";
		
	}
	
	@GetMapping("/cargo/actualizar")
	public String actualizarCargo(Model model) {
		model.addAttribute("cargo", new Cargo());
		return "actualizar-cargo";
	}
	
	@PostMapping("/cargo/actualizar")
	public String actualizarCargo(@RequestParam(name="opcion") String opcion,
			@ModelAttribute Cargo cargo,
			Model model) {
		
		Optional<Cargo> reg = repoCargo.findById(cargo.getIdCargo());		
		
		if(opcion.equals("b")) {
			if(reg.isPresent()) {
				cargo = reg.get();
				model.addAttribute("cargo", cargo);
			} else {
				model.addAttribute("mensajeCargo", "Cargo no encontrado");
			}
			
			return "actualizar-cargo";				
		}
		
		try {
			if(reg.isPresent()) {
				repoCargo.save(cargo);				
				model.addAttribute("mensajeRegistro", "Cargo actualizado.");
			} else {				
				model.addAttribute("mensajeRegistro", "Error al actualiar cargo, codigo de cargo no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error al actualizar cargo. " + e.getMessage());
			e.printStackTrace();
		}
		return "actualizar-cargo";
		
	}
	
	@GetMapping("/cargo/listar")
	public String listadoProductos(Model model) {
		
		model.addAttribute("lstCargos", repoCargo.findAll());
		return "listar-cargos";
		
	}
	
	private String codigoCorrelativo(String idUltimoCargo) {
		String codigoCorrelativo = "";
		char[] codigo= idUltimoCargo.toCharArray();
		int contador = 0;
		int nroCargo = 0;
		for(char a : codigo){
			contador++;
			if( a != 'C' && a != '0') {
				nroCargo = Integer.parseInt(idUltimoCargo.substring(contador - 1, 3)) + 1;
				break;
			}else if (contador == 3) {
				return codigoCorrelativo = "C0001";
			}
		}
		
		if(nroCargo < 10) {
			codigoCorrelativo = "C0" + nroCargo;
		}else {
			codigoCorrelativo = "C" + nroCargo;
		}		
		return codigoCorrelativo;
	}
	
}
