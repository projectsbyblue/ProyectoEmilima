package com.emilima.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emilima.model.Contrato;

@Repository
public interface IContratoRepository extends JpaRepository<Contrato, String> {
	
	/*
	 * Interface de Contrato
	 */
	
	/*
	 * 1 - Registro de contrato manejado por 		.save()
	 * 2 - Listado de contratos manejado por 		.findAll()
	 * 3 - Actualizar contrato manejado por 		.update()
	 * 4 - Buscar contrato manejado por 			.findById()
	 * 5 - Eliminar contrato manejado por 			.delete()
	 * 6 - Buscar ultimo contrato manejado po 		.findLastContrato()
	 * 7 - Listar contratos por fechas manejado por .findByFechaRegBetween()
	 *  
	 */
	
	// Buscar ultimo contrato
	@Query(value="select idContrato from contratos order by idContrato desc limit 1", nativeQuery = true)
	String findLastContrato();
	
	// Listar contratos entre fechas de registro
	ArrayList<Contrato> findByFechaRegBetween(String fecha1, String fecha2);

}
