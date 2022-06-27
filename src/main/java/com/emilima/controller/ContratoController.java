package com.emilima.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilima.model.Contrato;
import com.emilima.model.Trabajador;
import com.emilima.repository.ICargoRepository;
import com.emilima.repository.IContratoRepository;
import com.emilima.repository.ITrabajadorRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ContratoController {
	
	@Autowired
	IContratoRepository contratoRepo;
	
	@Autowired
	ITrabajadorRepository trabajadorRepo;
	
	@Autowired
	ICargoRepository cargoRepo;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/contrato/registro")
	public String registro(Model model) {
		Trabajador t = new Trabajador();
		t.setDni("12345678");
		model.addAttribute("trabajador", t);
		model.addAttribute("contrato", new Contrato());
		model.addAttribute("listaCargos", cargoRepo.findAll());
		
		return "elaborar-contrato";
	}
	
	@PostMapping("/contrato/registro")
	public String registrar(@RequestParam(name="opcion") String opcion,
			@ModelAttribute Trabajador trabajador,
			@ModelAttribute Contrato contrato,
			@RequestParam(name = "tipoContrato") String tipoContrato,
			HttpServletRequest request,
			Model model) {
		
		if(opcion.equals("buscar")) {
			trabajador = trabajadorRepo.findByDni(trabajador.getDni());
			if(trabajador == null) {
				model.addAttribute("mensajeTrabajador", "Trabajador no encontrado");
			} else {
				model.addAttribute("trabajador", trabajador);
			}
			
			model.addAttribute("listaCargos", cargoRepo.findAll());
			
			return "elaborar-contrato";				
		}
		
		
		try {
			if(trabajadorRepo.findByDni(trabajador.getDni()) != null) {
				trabajador = trabajadorRepo.findByDni(trabajador.getDni());
			} else {
				trabajador.setIdTrabajador(codigoCorrelativoTrabajador(trabajadorRepo.findLastTrabajador()));
			}
			
			trabajadorRepo.save(trabajador);
			
			contrato.setIdContrato(codigoCorrelativoContrato(contratoRepo.findLastContrato()));
			contrato.setTrabajador(trabajador);
			contrato.setTipo(tipoContrato);
			String rutaContrato = generarContrato(request, contrato);			
			contrato.setContratoPdf(rutaContrato);
			
			model.addAttribute("mensajeRegistro","Contrato registrado");
			model.addAttribute("rutacontrato", rutaContrato);
			
			contratoRepo.save(contrato);
			
		} catch (Exception e) {
			model.addAttribute("mensajeRegistro","Error al registrar contrato: " + e.getMessage());
		}
		
		model.addAttribute("listaCargos", cargoRepo.findAll());
		
		return "elaborar-contrato";
	}

	@GetMapping("/contrato/lista")
	public String listar(@ModelAttribute Contrato contrato, Model model) {
		model.addAttribute("listaContratos", contratoRepo.findAll());
		model.addAttribute("contrato", new Contrato());
		return "buscar-contrato";
	}
	
	@PostMapping("/contrato/buscar")
	public String buscar(@ModelAttribute Contrato contrato, Model model) {
		System.out.println(contrato.getIdContrato());
		ArrayList<Contrato> listaContratos = new ArrayList<Contrato>();
		
		Optional<Contrato> reg = contratoRepo.findById(contrato.getIdContrato());
		
		if( reg.isPresent()) {
			contrato = reg.get();
			listaContratos.add(contrato);
			model.addAttribute("listaContratos", listaContratos);
		} else {
			model.addAttribute("mensajeContrato", "Contrato no encontrado");
			model.addAttribute("listaContratos", contratoRepo.findAll());
		}
		
		return "buscar-contrato";
	}
	
	@PostMapping("/contrato/actualizar")
	public String actualizar(@RequestParam(name = "id") String id,Model model) {
		// validar archivo y guardar
		Contrato contrato = null;
		Optional<Contrato> reg = contratoRepo.findById(id);
		
		if(!reg.isPresent()) {
			model.addAttribute("mensaje", "Error al actualizar contrato");
			return "buscar-contrato";
		}
		
		contrato = reg.get();
		contrato.setEstado("Visado y Firmado");
		contrato.setContratoPdf("");
		
		contratoRepo.save(contrato);
		
		return "buscar-contrato";
	}
	
	private String codigoCorrelativoContrato(String idContrato) {
		String codigoCorrelativo = "";
		char[] codigo= idContrato.toCharArray();
		int contador = 0;
		int nroContrato = 0;
		for(char a : codigo){
			contador++;
			if( a != 'C' && a != '0') {
				nroContrato = Integer.parseInt(idContrato.substring(contador - 1, 5)) + 1;
				break;
			}else if (contador == 5) {
				return codigoCorrelativo = "C0001";
			}
		}
		
		if(nroContrato < 10) {
			codigoCorrelativo = "C000" + nroContrato;
		}else if (nroContrato < 100) {
			codigoCorrelativo = "C00" + nroContrato;
		} else if (nroContrato <1000) {
			codigoCorrelativo = "C0" + nroContrato;
		}else {
			codigoCorrelativo = "C" + nroContrato;
		}
		
		return codigoCorrelativo;
	}
	
	private String codigoCorrelativoTrabajador(String idTrabajador) {
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
	
	public String generarContrato(HttpServletRequest request, Contrato contrato) {			
		String nombre = "/contrato_"+ contrato.getTipo() + "_" + contrato.getIdContrato() + ".pdf";
		String rutaContratos = "";
		String plantilla = "";
		String url = "";
		
		try {
		
			if(contrato.getTipo().equals("asignacion")) {
				rutaContratos = ResourceUtils.getFile("classpath:static/contratos/asignacion/").getPath();			
				plantilla = resourceLoader.getResource("classpath:/static/reportes/contratoasignacionplaza.jasper").getURI().getPath();
				url = "/contratos/asignacion";
			} else {
				rutaContratos = ResourceUtils.getFile("classpath:static/contratos/renovacion/").getPath();			
				plantilla = resourceLoader.getResource("classpath:/static/reportes/contratorenovacioncontrato.jasper").getURI().getPath();
				url = "/contratos/renovacion";
			}
			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idcontrato",contrato.getIdContrato());

			JasperPrint jasperPrint = JasperFillManager.fillReport(plantilla, parametros, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jasperPrint, rutaContratos + nombre);
			
			return url + nombre;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
