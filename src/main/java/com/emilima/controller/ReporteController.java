package com.emilima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {
	
	@GetMapping("/reporte/contratos")
	public String reporteContratos() {
		return "reporte-contratos";
	}
}
