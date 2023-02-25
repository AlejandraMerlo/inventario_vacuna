package com.inventario.modules.roles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.Rol;
import com.inventario.modules.roles.service.RolService;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/roles")
public class RolController {

	@Autowired
	private RolService rolService;

	@GetMapping()
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getRoles() {
		try {
			List<Rol> roles = this.rolService.getRoles();
			return HttpResponseService.responseOK(roles);
		} catch (Exception exception) {
			String accion = "Get Roles.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
