package com.inventario.modules.usuarios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.Usuario;
import com.inventario.modules.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = this.usuarioRepository.getUsuarios();
		return usuarios;
	}

	public Usuario getUsuarioByCodigo(int codigoUsuario) {
		Usuario usuario = new Usuario();
		usuario = this.usuarioRepository.getUsuarioByCodigo(codigoUsuario);
		return usuario;
	}

	public boolean validarExistenciaUsuario(int codigoUsuario) {
		boolean respuesta = this.getUsuarioByCodigo(codigoUsuario) != null;
		return respuesta;
	}

	public Usuario getUsuarioByNombre(String nombreUsuario) {
		Usuario usuario = new Usuario();
		usuario = this.usuarioRepository.getUsuarioByNombre(nombreUsuario);
		return usuario;
	}

	public String getNombreUsuario(int codigoUsuario) {
		String nombreUsuario = "";
		Usuario usuario = this.getUsuarioByCodigo(codigoUsuario);
		if (usuario != null) {
			nombreUsuario = usuario.getEmpleado().getPersona().getNombrePersona();
		}
		return nombreUsuario;
	}

	private Usuario updateUsuario(Usuario usuario) {
		usuario = this.usuarioRepository.save(usuario);
		return usuario;
	}

}
