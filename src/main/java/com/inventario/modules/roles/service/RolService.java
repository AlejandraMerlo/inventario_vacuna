package com.inventario.modules.roles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.Rol;
import com.inventario.modules.roles.repository.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public List<Rol> getRoles() {
		List<Rol> roles = new ArrayList<>();
		roles = this.rolRepository.getRols();
		return roles;
	}

	public Rol getRolByCodigo(int codigoRol) {
		Rol rol = new Rol();
		rol = this.rolRepository.getRolByCodigo(codigoRol);
		return rol;
	}

	private Rol updateRol(Rol rol) {
		rol = this.rolRepository.save(rol);
		return rol;
	}
}
