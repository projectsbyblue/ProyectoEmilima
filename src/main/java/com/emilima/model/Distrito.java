package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="distritos")
public class Distrito {
	@Id
	private int idDistrito;
	private String distrito;
	private int idProvincia;
	public Distrito() {
		super();
	}
	public Distrito(int idDistrito, String distrito, int idProvincia) {
		super();
		this.idDistrito = idDistrito;
		this.distrito = distrito;
		this.idProvincia = idProvincia;
	}
	@Override
	public String toString() {
		return "Distrito [idDistrito=" + idDistrito + ", distrito=" + distrito + ", idProvincia=" + idProvincia + "]";
	}
	public int getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	
	
	
	
}
