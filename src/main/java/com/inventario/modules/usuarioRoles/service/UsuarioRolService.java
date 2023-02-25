package com.inventario.modules.usuarioRoles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.UsuarioRol;
import com.inventario.modules.usuarioRoles.repository.UsuarioRolRepository;

@Service
public class UsuarioRolService {

	@Autowired
	private UsuarioRolRepository usuarioRolRepository;

	public UsuarioRol getUsuarioRolByCodigo(int usuarioRol) {
		UsuarioRol usuRol = new UsuarioRol();
		usuRol = this.usuarioRolRepository.getUsuarioRolByCodigo(usuarioRol);
		return usuRol;
	}

	public UsuarioRol getUsuarioRolByCodigoUsuario(int codigoUsuario) {
		UsuarioRol usuRol = new UsuarioRol();
		usuRol = this.usuarioRolRepository.getUsuarioRolByUsuario(codigoUsuario);
		return usuRol;
	}

	private UsuarioRol updateUsuarioRol(UsuarioRol usuRol) {
		usuRol = this.usuarioRolRepository.save(usuRol);
		return usuRol;
	}

}
