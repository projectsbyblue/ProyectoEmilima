package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="trabajadores")
@Data
public class Trabajador {
	
	@Id
	private String idTrabajador;
	private String dni;
	private String nombres;
	private String apellidos;
	private String direccion;
	private int idCiudad;
	private int idProvincia;
	private int idDistrito;
	
}
