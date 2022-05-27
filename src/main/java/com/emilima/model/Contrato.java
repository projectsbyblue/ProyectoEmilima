package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "contratos")
@Data
public class Contrato {

	@Id
	private String idContrato;
	
	@OneToMany
	@JoinColumn(name = "idTrabajador")
	private Trabajador trabajador;
	private String tipo;
	private String motivo;

	@Column(name = "idCargo")
	private String cargo;

	@Column(name = "fecInicio")
	private String fechaInicio;

	private String horaIng;
	private String horaSal;
	private String fechaReg;
	private String contratoPdf;
	private String estado;
	private int temporada;

	@Column(name = "perPrueba")
	private int periodoPrueba;
	private double remuneracion;
	
}
