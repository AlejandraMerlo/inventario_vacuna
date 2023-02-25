package com.inventario.modules.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.Empleado;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.empleados.service.EmpleadoService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getEmpleados() {
		try {
			List<Empleado> empleados = this.empleadoService.getEmpleados();
			return HttpResponseService.responseOK(empleados);
		} catch (Exception exception) {
			String accion = "Get Empleados.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@GetMapping(value = "/empleado/{codigoEmpleado}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getEmpleadoByCodigo(@PathVariable("codigoEmpleado") int codigoEmpleado) {
		try {
			Empleado empleado = this.empleadoService.getEmpleadoByCodigo(codigoEmpleado);
			return HttpResponseService.responseOK(empleado);
		} catch (Exception exception) {
			String accion = "Get Empleado By código.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
