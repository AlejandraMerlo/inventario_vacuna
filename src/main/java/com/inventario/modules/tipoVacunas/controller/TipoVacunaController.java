package com.inventario.modules.tipoVacunas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.TipoVacuna;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.tipoVacunas.service.TipoVacunaService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/tipoVacunas")
public class TipoVacunaController {

	@Autowired
	private TipoVacunaService tipoVacunaService;

	@GetMapping()
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getTipoVacunas() {
		try {
			List<TipoVacuna> tiposVacunas = this.tipoVacunaService.getTiposVacunas();
			return HttpResponseService.responseOK(tiposVacunas);
		} catch (Exception exception) {
			String accion = "Get TiposVacuna.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
