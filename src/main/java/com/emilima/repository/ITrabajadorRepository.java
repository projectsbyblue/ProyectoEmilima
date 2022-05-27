package com.emilima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emilima.model.Trabajador;

@Repository
public interface ITrabajadorRepository extends JpaRepository<Trabajador, String> {
	
	/*
	 * Interface de Trabajador
	 */
	
	/*
	 * 1 - Registrar trabajador manejado por 		.save()
	 * 2 - Buscar trabajador por dni manejado por 	.findByDni()
	 * 3 - Buscar trabajador por id manejado por 	.findById()
	 * 4 - Buscar ultimo trabajador manejado por	.findLastTrabajador()
	 * 5 - Actualizar trabajador manejado por 		.save()
	 * 6 - Listar trabajadores manejado por 		.findAll() 
	 * 
	 */
	
	// Buscar trabajador por DNI
	Trabajador findByDni(String dni);
	
	// Buscar ultimo trabajador
	@Query("select idTrabajador from trabajadores order by idTrabajador desc limit 1")
	String findLastTrabajador();

}
