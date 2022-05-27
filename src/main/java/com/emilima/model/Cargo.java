package com.emilima.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cargos")
@Data
public class Cargo {
	
	@Id
	private String idCargo;	
	private String cargo;
	
}
