package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargos")
public class Cargo {
	@Id

	private String idCargo;
	
	private String cargo;

	public Cargo() {
		super();
	}

	public Cargo(String idCargo, String cargo) {
		super();
		this.idCargo = idCargo;
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Cargo [idCargo=" + idCargo + ", cargo=" + cargo + "]";
	}

	public String getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
	
}
