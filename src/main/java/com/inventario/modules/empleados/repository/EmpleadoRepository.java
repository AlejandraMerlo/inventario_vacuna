package com.inventario.modules.empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	@Query(value = "SELECT empleado FROM Empleado empleado ORDER BY empleado.persona.apellidos ASC")
	public List<Empleado> getEmpleados();

	@Query(value = "SELECT empleado FROM Empleado empleado WHERE empleado.codigoEmpleado = :codigoEmpleado")
	public Empleado getEmpleadoByCodigo(@Param("codigoEmpleado") int codigoEmpleado);

}
