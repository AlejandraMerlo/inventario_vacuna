package com.inventario.modules.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.dto.EmpleadoDto;
import com.inventario.modules.empleados.service.EmpleadoService;
import com.inventario.modules.models.Empleado;
import com.inventario.modules.models.Persona;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
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

	@GetMapping(value = "/vacunado/{vacunados}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getEmpleadosByEstadoVacunacion(@PathVariable("vacunados") Boolean vacunados) {
		try {
			List<Empleado> empleados = this.empleadoService.getEmpleadosByEstadoVacuna(vacunados);
			return HttpResponseService.responseOK(empleados);
		} catch (Exception exception) {
			String accion = "Get EmpleadoS By estadoVacunación.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@PostMapping(value = "/tipoVacuna/")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getEmpleadosByTipoVacuna(@RequestBody EmpleadoDto empleadoDto) {
		try {
			List<Empleado> empleados = this.empleadoService.getEmpleadosByTipoVacuna(empleadoDto.getTipoVacuna());
			return HttpResponseService.responseOK(empleados);
		} catch (Exception exception) {
			String accion = "POST EmpleadoS By TipoVacuna.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@PostMapping(value = "/fechasVacunacion/")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getEmpleadosFechasVacuna(@RequestBody EmpleadoDto empleadoDto) {
		try {
			List<Empleado> empleados = this.empleadoService.getEmpleadosByTipoVacuna(empleadoDto.getTipoVacuna());
			return HttpResponseService.responseOK(empleados);
		} catch (Exception exception) {
			String accion = "POST EmpleadoS By Fechas vacunación.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@PostMapping(value = "/create")
	@JsonView({ Views.Write.class })
	public ResponseEntity<?> createEmpleado(@RequestBody EmpleadoDto empleadoDto) {
		try {
			if (empleadoDto == null)
				throw new Exception("No se encuentra información.");

			Empleado empleado = empleadoDto.getEmpleado();
			Persona persona = empleadoDto.getPersona();
			if (empleado == null || persona == null)
				throw new Exception("No se han encontrado registros.");

			empleado = this.empleadoService.createEmpleadoUsuario(empleadoDto);
			return HttpResponseService.responseOK(empleado);
		} catch (Exception exception) {
			String accion = "Create nuevo Empleado.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@PutMapping(value = "/update")
	@JsonView({ Views.Write.class })
	public ResponseEntity<?> actualizarEmpleado(@RequestBody Empleado empleado) {
		try {
			if (empleado == null)
				throw new Exception("El empleado se encuentra vacío.");

			empleado = this.empleadoService.updateEmpleado(empleado);
			return HttpResponseService.responseOK(empleado);
		} catch (Exception exception) {
			String accion = "Update empleado.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@DeleteMapping
	@JsonView({ Views.Write.class })
	public ResponseEntity<?> deleteEmpleado(@RequestBody Empleado empleado) {
		try {
			if (empleado == null)
				throw new Exception("El registro está vacío.");

			this.empleadoService.deleteEmpleado(empleado);
			return HttpResponseService.responseOK();
		} catch (Exception exception) {
			String accion = "Delete Empleado.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
