package com.inventario.modules.empleados.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.empleados.repository.EmpleadoRepository;
import com.inventario.modules.models.Empleado;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	public List<Empleado> getEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		empleados = this.empleadoRepository.getEmpleados();
		return empleados;
	}

	public Empleado getEmpleadoByCodigo(int codigoEmpleado) {
		Empleado empleado = new Empleado();
		empleado = this.empleadoRepository.getEmpleadoByCodigo(codigoEmpleado);
		return empleado;
	}

	public boolean validarExistenciaEmpleado(int codigoEmpleado) {
		boolean respuesta = this.getEmpleadoByCodigo(codigoEmpleado) != null;
		return respuesta;
	}

	public String getNombreEmpleado(int codigoEmpleado) {
		String nombreEmpleado = "";
		Empleado empleado = this.getEmpleadoByCodigo(codigoEmpleado);
		if (empleado != null) {
			nombreEmpleado = empleado.getPersona().getNombrePersona();
		}
		return nombreEmpleado;
	}

	private Empleado updateEmpleado(Empleado empleado) {
		empleado = this.empleadoRepository.save(empleado);
		return empleado;
	}

}
