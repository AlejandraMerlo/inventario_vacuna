package com.inventario.modules.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT usuario FROM Usuario usuario ORDER BY usuario ASC")
	public List<Usuario> getUsuarios();

	@Query(value = "SELECT usuario FROM Usuario usuario WHERE usuario.codigoUsuario = :codigoUsuario")
	public Usuario getUsuarioByCodigo(@Param("codigoUsuario") int codigoUsuario);

	@Query(value = "SELECT usuario FROM Usuario usuario WHERE usuario.usuario = :nombreUsuario ")
	public Usuario getUsuarioByNombre(@Param("nombreUsuario") String nombeUsuario);

	@Query(value = "SELECT usuario FROM Usuario usuario WHERE usuario.empleado.codigoEmpleado = :codigoEmpleado")
	public List<Usuario> getUsuariosBYEmpleado(@Param("codigoEmpleado") int codigoEmpleado);
}
