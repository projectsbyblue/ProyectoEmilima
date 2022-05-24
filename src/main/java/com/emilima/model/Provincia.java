package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="provincias")
public class Provincia {
	@Id
	private int idProvincia;
	private String provincia;
	private int idCiudad;
	public Provincia() {
		super();
	}
	public Provincia(int idProvincia, String provincia, int idCiudad) {
		super();
		this.idProvincia = idProvincia;
		this.provincia = provincia;
		this.idCiudad = idCiudad;
	}
	@Override
	public String toString() {
		return "Provincia [idProvincia=" + idProvincia + ", provincia=" + provincia + ", idCiudad=" + idCiudad + "]";
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	
	
	
}
