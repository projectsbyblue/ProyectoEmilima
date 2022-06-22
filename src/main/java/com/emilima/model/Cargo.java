package com.emilima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cargos")
@Data
public class Cargo {
	
	@Id
	@Column(name = "idcargo")
	private String idCargo;	
	private String cargo;
	
}
