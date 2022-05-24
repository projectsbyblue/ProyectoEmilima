package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ciudades")
public class Ciudad {
	@Id
	private int idCiudad;
	private String ciudad;
	public Ciudad() {
		super();
	}
	public Ciudad(int idCiudad, String ciudad) {
		super();
		this.idCiudad = idCiudad;
		this.ciudad = ciudad;
	}
	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", ciudad=" + ciudad + "]";
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
}
