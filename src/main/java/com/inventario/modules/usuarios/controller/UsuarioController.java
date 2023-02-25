package com.inventario.modules.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.models.Usuario;
import com.inventario.modules.services.ErrorService;
import com.inventario.modules.services.HttpResponseService;
import com.inventario.modules.usuarios.service.UsuarioService;
import com.inventario.modules.views.Views;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getUsuarios() {
		try {
			List<Usuario> usuarios = this.usuarioService.getUsuarios();
			return HttpResponseService.responseOK(usuarios);
		} catch (Exception exception) {
			String accion = "Get Usuarios.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@GetMapping(value = "/usuario/{codigoUsuario}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getUsuarioByCodigo(@PathVariable("codigoUsuario") int codigoUsuario) {
		try {
			Usuario usuario = this.usuarioService.getUsuarioByCodigo(codigoUsuario);
			return HttpResponseService.responseOK(usuario);
		} catch (Exception exception) {
			String accion = "Get Usuario By código.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

	@GetMapping(value = "/nombre/{nombreUsuario}")
	@JsonView({ Views.Read.class })
	public ResponseEntity<?> getUsuarioByNombre(@PathVariable("nombreUsuario") String nombreUsuario) {
		try {
			Usuario usuario = this.usuarioService.getUsuarioByNombre(nombreUsuario);
			return HttpResponseService.responseOK(usuario);
		} catch (Exception exception) {
			String accion = "Get Usuario By usuario.";
			log.error(accion, exception);
			return HttpResponseService
					.responseInternalError(new ErrorService(exception.getMessage(), accion, exception));
		}
	}

}
