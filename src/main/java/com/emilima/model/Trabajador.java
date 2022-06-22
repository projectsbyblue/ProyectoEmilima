package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="trabajadores")
@Data
public class Trabajador {
	
	@Id
	@Column(name = "idtrabajador")
	private String idTrabajador;
	private String dni;
	private String nombres;
	private String apellidos;
	private String direccion;
	@Column(name = "idciudad")
	private int idCiudad;
	@Column(name = "idprovincia")
	private int idProvincia;
	@Column(name = "iddistrito")
	private int idDistrito;
	
}
