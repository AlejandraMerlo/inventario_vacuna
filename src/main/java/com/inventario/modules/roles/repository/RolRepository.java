package com.inventario.modules.roles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

	@Query(value = "SELECT rol FROM Rol rol ORDER BY rol.nombreRol ASC")
	public List<Rol> getRols();

	@Query(value = "SELECT rol FROM Rol rol WHERE rol.codigoRol = :codigoRol")
	public Rol getRolByCodigo(@Param("codigoRol") int codigoRol);

}
