package com.emilima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emilima.model.Perfil;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
	
	/*
	 * Interface de Perfil
	 */
	
	/*
	 * 1 - Buscar perfil por usuario manejado por 	.finByUsuario
	 * 2 - Actualizar perfil manejado por 			.update()
	 * 
	 */
	
	// Buscar perfil por usuario
	Perfil findByUsuario(String usuario);

}
