package com.inventario.modules.usuarioRoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.UsuarioRol;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.usuarioRoles.service.UsuarioRolService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/usuarioRoles")
public class UsuarioRolController {

	@Autowired
	private UsuarioRolService usuarioRolService;

	@GetMapping(value = "/codigo/{usuarioRol}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getUsuarioRolByCodigo(@PathVariable("usuarioRol") int usuarioRol) {
		try {
			UsuarioRol usuRol = this.usuarioRolService.getUsuarioRolByCodigo(usuarioRol);
			return HttpResponseService.responseOK(usuRol);
		} catch (Exception exception) {
			String accion = "Get RolUsuario by código.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@GetMapping(value = "/rol/{codigoUsuario}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getUsuarioRolByUsuario(@PathVariable("codigoUsuario") int codigoUsuario) {
		try {
			UsuarioRol usuRol = this.usuarioRolService.getUsuarioRolByCodigoUsuario(codigoUsuario);
			return HttpResponseService.responseOK(usuRol);
		} catch (Exception exception) {
			String accion = "Get UsuarioRol By código Usuario.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
