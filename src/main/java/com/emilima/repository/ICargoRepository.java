package com.emilima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emilima.model.Cargo;

@Repository
public interface ICargoRepository extends JpaRepository<Cargo, String> {
	
	/*
	 * Interface de Cargo
	 */
	
	/*
	 * 1 - Listar cargos manejado por 		.finAll()
	 * 2 - Registrar cargo manejado por 	.save()
	 * 3 - Actualizar cargo manejado por 	.update()
	 * 4 - Buscar cargo manejado por 		.finById()
	 * 5 - Buscar ultimo cargo manejado por .findLastCargo()
	 */
	
	// Buscar ultimo cargo
	@Query(value="select idCargo from cargos order by idCargo desc limit 1", nativeQuery = true)
	String findLastCargo();
	
}
