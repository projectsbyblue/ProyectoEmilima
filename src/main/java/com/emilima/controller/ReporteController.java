package com.emilima.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilima.model.Contrato;
import com.emilima.repository.ICargoRepository;
import com.emilima.repository.IContratoRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReporteController {
	
	@Autowired
	ICargoRepository cargoRepo;
	
	@Autowired
	IContratoRepository contratoRepo;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/reporte/contratos")
	public String reporteContratos(Model model) {
		model.addAttribute("listaCargos", cargoRepo.findAll());
		return "reporte-contratos";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/reporte/contratos")
	public String reporteContratos(
			HttpServletRequest request,
			@RequestParam("fecha1") String fecha1,
			@RequestParam("fecha2") String fecha2,
			@RequestParam("tipo") String tipoContrato,
			@RequestParam("cargo") String cargo,
			@RequestParam("estado") String estadoContrato,			
			Model model) {
		
		int contador = 0;
		String mensaje = "";
		String rutaReporte = "";
		
		ArrayList<Contrato> listaContratos = contratoRepo.findByFechaRegBetween(fecha1, fecha2);
		ArrayList<Contrato> listaContratosOut = new ArrayList<Contrato>();
		
		if(listaContratos != null) {
			if (!tipoContrato.equals("-1")) {
				contador = 1;
				if(listaContratos.size() != 0) {
					for(Contrato contrato : listaContratos) {
						if(contrato.getTipo().equals(tipoContrato)) {
							listaContratosOut.add(contrato);
						}
					}
					listaContratos.clear();
					listaContratos = (ArrayList<Contrato>) listaContratosOut.clone();
					listaContratosOut.clear();
				}
			}
			
			if (!cargo.equals("-1")) {
				if (contador == 1 || contador == 2) {
					contador = 2;
					if(listaContratos.size() != 0) {
						for(Contrato contrato : listaContratos) {
							if(contrato.getIdcargo().equals(cargo)) {
								listaContratosOut.add(contrato);
							}
						}
						listaContratos.clear();
						listaContratos = (ArrayList<Contrato>) listaContratosOut.clone();
						listaContratosOut.clear();
					}
				}else {	
					contador = 1;
					if(listaContratos.size() != 0) {
						for(Contrato contrato : listaContratos) {
							if(contrato.getIdcargo().equals(cargo)) {
								listaContratosOut.add(contrato);
							}
						}
						listaContratos.clear();
						listaContratos = (ArrayList<Contrato>) listaContratosOut.clone();
						listaContratosOut.clear();
					}
				}
			}
			
			if (!estadoContrato.equals("-1")) {
				if (contador == 1 || contador == 2) {
					if(listaContratos.size() != 0) {
						for(Contrato contrato : listaContratos) {
							if(contrato.getEstado().equals(estadoContrato)) {
								listaContratosOut.add(contrato);
							}
						}	
						listaContratos.clear();
						listaContratos = (ArrayList<Contrato>) listaContratosOut.clone();
						listaContratosOut.clear();
					}
				} else {	
					if(listaContratos.size() != 0) {
						for(Contrato contrato : listaContratos) {
							if(contrato.getEstado().equals(estadoContrato)) {
								listaContratosOut.add(contrato);								
							}
						}
						listaContratos.clear();
						listaContratos = (ArrayList<Contrato>) listaContratosOut.clone();
						listaContratosOut.clear();
					}
				}
				
			}
		}
		
		int lista = listaContratos.size();		
		
		if(lista != 0 ) {
			if(contador == 0) {
				model.addAttribute("listaContratos", listaContratos);
				rutaReporte = generarReporte(request, fecha1, fecha2, tipoContrato, cargo, estadoContrato);
			} else {
				model.addAttribute("listaContratos", listaContratos);
				rutaReporte = generarReporte(request, fecha1, fecha2, tipoContrato, cargo, estadoContrato);
			}
			model.addAttribute("reporte", rutaReporte);
		} else {
			mensaje = "No se encontraron contratos";
			model.addAttribute("mensaje", mensaje);
		}		
		
		model.addAttribute("listaCargos", cargoRepo.findAll());		
		return "reporte-contratos";
	}
	
	private String generarReporte(HttpServletRequest request, String fecha1, String fecha2, String tipo, String cargo, String estado) {
		String nombre = "/reporte_"+ fecha1 + "_" + fecha2 + ".pdf";
		String rutaContratos = "";
		String plantilla = "";
		String url = "";
		File dir = null;
		
		try {
		
			dir = new File(ResourceUtils.getFile("classpath:static").getPath() + "/reporte/contratos/");
			if(!dir.exists()) {
				dir.mkdirs();					
			}
			
			rutaContratos = ResourceUtils.getFile("classpath:static/reporte/contratos/").getPath();			
			plantilla = resourceLoader.getResource("classpath:static/reportes/reportecontratos.jasper").getURI().getPath();
			url = "/reporte/contratos";
			
			
			if(tipo.equals("-1")) tipo = "%";
			if(cargo.equals("-1")) cargo = "%";
			if(estado.equals("-1")) estado = "%";
			
			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fecha1", fecha1);
			parametros.put("fecha2", fecha2);
			parametros.put("tipo", tipo);
			parametros.put("cargo", cargo);
			parametros.put("estado", estado);
			
			System.out.println(parametros);

			JasperPrint jasperPrint = JasperFillManager.fillReport(plantilla, parametros, dataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jasperPrint, rutaContratos + nombre);
			
			return url + nombre;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
