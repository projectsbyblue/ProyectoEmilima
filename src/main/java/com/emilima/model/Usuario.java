package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="usuarios")
@Data
public class Usuario {
	@Id
	private String usuario;
	private String clave;	
	
}
