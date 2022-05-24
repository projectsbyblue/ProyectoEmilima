package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles")
public class Perfil {
	@Id
	private int idPersonal;
	private String nombres;
	private String apellidos;
	private String	dni;
	private String	foto;
	private String	rol;
	private String	usuario;
	public Perfil() {
		super();
	}
	public Perfil(int idPersonal, String nombres, String apellidos, String dni, String foto, String rol,
			String usuario) {
		super();
		this.idPersonal = idPersonal;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.foto = foto;
		this.rol = rol;
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Perfil [idPersonal=" + idPersonal + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", foto=" + foto + ", rol=" + rol + ", usuario=" + usuario + "]";
	}
	public int getIdPersonal() {
		return idPersonal;
	}
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
