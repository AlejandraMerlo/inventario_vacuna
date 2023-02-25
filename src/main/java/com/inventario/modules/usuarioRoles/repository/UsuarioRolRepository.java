package com.inventario.modules.usuarioRoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.UsuarioRol;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {

	@Query(value = "SELECT usuRol FROM UsuarioRol usuRol WHERE usuRol.usuarioRol = :usuarioRol")
	public UsuarioRol getUsuarioRolByCodigo(@Param("usuarioRol") int usuarioRol);

	@Query(value = "SELECT usuRol FROM UsuarioRol usuRol WHERE usuRol.usuario.codigoUsuario = :codigoUsuario")
	public UsuarioRol getUsuarioRolByUsuario(@Param("codigoUsuario") int codigoUsuario);
}
