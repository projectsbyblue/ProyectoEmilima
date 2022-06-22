package com.emilima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilima.model.Contrato;
import com.emilima.model.Trabajador;
import com.emilima.repository.IContratoRepository;
import com.emilima.repository.ITrabajadorRepository;

@Controller
public class ContratoController {
	
	@Autowired
	IContratoRepository contratoRepo;
	
	@Autowired
	ITrabajadorRepository trabajadorRepo;
	
	@GetMapping("/contrato/registro")
	public String registro(Model model) {
		Trabajador t = new Trabajador();
		t.setDni("12345678");
		model.addAttribute("trabajador", t);
		model.addAttribute("contrato", new Contrato());
		
		return "elaborar-contrato";
	}
	
	@PostMapping("/contrato/registrar")
	public String registrar(@ModelAttribute Trabajador trabajador, @ModelAttribute Contrato contrato, Model model) {
		try {
			// transaccion
			trabajadorRepo.save(trabajador);
			contrato.setTrabajador(trabajador);
			contratoRepo.save(contrato);
			model.addAttribute("mensaje","Contrato registrado");
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje","Error al registrar contrato");
		}
		return "elaborar-contrato";
	}
	
	@PostMapping("/contrato/registro")
	public String buscarTrabajador(@ModelAttribute Trabajador trabajador, Model model) {
		trabajador = trabajadorRepo.findByDni(trabajador.getDni());
		
		if(trabajador == null) {
			model.addAttribute("mensajetrabajador", "Trabajador no encontrado");
		}
		model.addAttribute("trabajador", trabajador);
		return "elaborar-contrato";
	}
	
	@GetMapping("/contrato/lista")
	public String listar(Model model) {
		model.addAttribute("listaContratos", contratoRepo.findAll());
		return "buscar-contrato";
	}
	
	@PostMapping("/contrato/buscar")
	public String buscar(@RequestParam(name = "codigo") String codigo, Model model) {
		model.addAttribute("listaContratos", contratoRepo.findById(codigo));
		return "buscar-contrato";
	}
	
	@PostMapping("/contrato/actualizar")
	public String actualizar(@RequestParam(name = "id") String id,Model model) {
		// validar archivo y guardar
		Contrato contrato = null;
		contrato = contratoRepo.findById(id).get();
		
		if(contrato == null) {
			model.addAttribute("mensaje", "Error al actualizar contrato");
		}
		
		contrato.setEstado("Visado y Firmado");
		contrato.setContratoPdf("");
		
		contratoRepo.save(contrato);
		
		return "buscar-contrato";
	}

}
