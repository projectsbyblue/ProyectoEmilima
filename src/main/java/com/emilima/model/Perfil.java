package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="perfiles")
@Data
public class Perfil {
	
	@Id
	@Column(name="idpersonal")
	private int idPersonal;
	private String nombres;
	private String apellidos;
	private String	dni;
	private String	foto;
	private String	rol;
	private String	usuario;
	
}
