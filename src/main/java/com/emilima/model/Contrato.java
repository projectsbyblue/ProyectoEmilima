package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Entity
@Table(name = "contratos")
@Data
@DynamicInsert
public class Contrato {

	@Id
	@Column(name = "idcontrato")
	private String idContrato;
	
	@ManyToOne
	@JoinColumn(name = "idtrabajador")
	private Trabajador trabajador;
	private String tipo;
	private String motivo;

	@Column(name = "idcargo")
	private String idcargo;

	@Column(name = "fecinicio")
	private String fechaInicio;
	@Column(name = "horaing")
	private String horaIng;
	@Column(name = "horasal")
	private String horaSal;
	@Column(name = "fechareg", updatable = false)
	private String fechaReg;
	@Column(name = "contratopdf")
	private String contratoPdf;
	@Column(name = "estado", updatable = false)
	private String estado;
	private int temporada;

	@Column(name = "perprueba")
	private int periodoPrueba;
	private double remuneracion;
	
}
