package com.inventario.modules.personas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.Persona;
import com.inventario.modules.personas.service.PersonaService;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping()
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getPersonas() {
		try {
			List<Persona> personas = this.personaService.getPersonas();
			return HttpResponseService.responseOK(personas);
		} catch (Exception exception) {
			String accion = "Get Personas.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@GetMapping(value = "/persona/{codigoPersona}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getPersonaByCodigo(@PathVariable("codigoPersona") int codigoPersona) {
		try {
			Persona persona = this.personaService.getPersonaByCodigo(codigoPersona);
			return HttpResponseService.responseOK(persona);
		} catch (Exception exception) {
			String accion = "Get Persona By código.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
