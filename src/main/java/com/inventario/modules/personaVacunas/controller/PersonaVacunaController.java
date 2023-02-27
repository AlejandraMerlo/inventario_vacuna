package com.inventario.modules.personaVacunas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.VacunaPersona;
import com.inventario.modules.personaVacunas.service.PersonaVacunaService;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/personaVacunas")
public class PersonaVacunaController {

	@Autowired
	private PersonaVacunaService personaVacunaService;

	@GetMapping(value = "/{codigoPersona}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getVacunasByPersona(@PathVariable("codigoPersona") int codigoPersona) {
		try {
			List<VacunaPersona> vacunas = this.personaVacunaService.getVacunasByPersona(codigoPersona);
			return HttpResponseService.responseOK(vacunas);
		} catch (Exception exception) {
			String accion = "Get Vacunas ByPersona.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
