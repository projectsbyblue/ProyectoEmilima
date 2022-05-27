package com.emilima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emilima.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
	
	/*
	 * Interface de Usuario
	 */
	
	/*
	 * 1 - Validar acceso de usuario manejado por 	.findByUsuarioAndClave()
	 * 2 - Actualizar usuario manejado por 			.update()
	 * 
	 */
	
	// Validar acceso por usuario y clave
	Usuario findByUsuarioAndClave(String usuario, String clave);

}
