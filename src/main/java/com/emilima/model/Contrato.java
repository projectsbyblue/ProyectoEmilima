package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contratos")
public class Contrato {

	@Id
	private String idContrato;
	private String idTrabajador;
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

	public Contrato() {
		super();
	}

	public Contrato(String idContrato, String idTrabajador, String tipo, String motivo, String cargo,
			String fechaInicio, String horaIng, String horaSal, String fechaReg, String contratoPdf, String estado,
			int temporada, int periodoPrueba, double remuneracion) {
		super();
		this.idContrato = idContrato;
		this.idTrabajador = idTrabajador;
		this.tipo = tipo;
		this.motivo = motivo;
		this.cargo = cargo;
		this.fechaInicio = fechaInicio;
		this.horaIng = horaIng;
		this.horaSal = horaSal;
		this.fechaReg = fechaReg;
		this.contratoPdf = contratoPdf;
		this.estado = estado;
		this.temporada = temporada;
		this.periodoPrueba = periodoPrueba;
		this.remuneracion = remuneracion;
	}

	@Override
	public String toString() {
		return "Contrato [idContrato=" + idContrato + ", idTrabajador=" + idTrabajador + ", tipo=" + tipo + ", motivo="
				+ motivo + ", cargo=" + cargo + ", fechaInicio=" + fechaInicio + ", horaIng=" + horaIng + ", horaSal="
				+ horaSal + ", fechaReg=" + fechaReg + ", contratoPdf=" + contratoPdf + ", estado=" + estado
				+ ", temporada=" + temporada + ", periodoPrueba=" + periodoPrueba + ", remuneracion=" + remuneracion
				+ "]";
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(String idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHoraIng() {
		return horaIng;
	}

	public void setHoraIng(String horaIng) {
		this.horaIng = horaIng;
	}

	public String getHoraSal() {
		return horaSal;
	}

	public void setHoraSal(String horaSal) {
		this.horaSal = horaSal;
	}

	public String getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}

	public String getContratoPdf() {
		return contratoPdf;
	}

	public void setContratoPdf(String contratoPdf) {
		this.contratoPdf = contratoPdf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public int getPeriodoPrueba() {
		return periodoPrueba;
	}

	public void setPeriodoPrueba(int periodoPrueba) {
		this.periodoPrueba = periodoPrueba;
	}

	public double getRemuneracion() {
		return remuneracion;
	}

	public void setRemuneracion(double remuneracion) {
		this.remuneracion = remuneracion;
	}

	
	
	
}
