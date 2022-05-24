package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trabajadores")
public class Trabajador {
	@Id
	private String idTrabajador;
	private String dni;
	private String nombres;
	private String apellidos;
	private String direccion;
	private int idCiudad;
	private String idProvincia;
	private String idDistrito;
	public Trabajador() {
		super();
	}
	public Trabajador(String idTrabajador, String dni, String nombres, String apellidos, String direccion, int idCiudad,
			String idProvincia, String idDistrito) {
		super();
		this.idTrabajador = idTrabajador;
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.idCiudad = idCiudad;
		this.idProvincia = idProvincia;
		this.idDistrito = idDistrito;
	}
	@Override
	public String toString() {
		return "Trabajador [idTrabajador=" + idTrabajador + ", dni=" + dni + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", direccion=" + direccion + ", idCiudad=" + idCiudad + ", idProvincia=" + idProvincia
				+ ", idDistrito=" + idDistrito + "]";
	}
	public String getIdTrabajador() {
		return idTrabajador;
	}
	public void setIdTrabajador(String idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}
	
	
	
	
}
