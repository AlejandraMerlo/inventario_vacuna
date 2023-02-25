package com.inventario.modules.personas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.Persona;
import com.inventario.modules.personas.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	public List<Persona> getPersonas() {
		List<Persona> persona = new ArrayList<>();
		persona = this.personaRepository.getPersonas();
		return persona;
	}

	public Persona getPersonaByCodigo(int codigoPersona) {
		Persona persona = new Persona();
		persona = this.personaRepository.getPersonaByCodigo(codigoPersona);
		return persona;
	}

	public String getNombrePersona(int codigoPersona) {
		String nombre = "";
		Persona persona = this.getPersonaByCodigo(codigoPersona);
		if (persona != null) {
			nombre = persona.getNombrePersona();
		}
		return nombre;
	}

	private Persona createPersona(Persona persona) {
		persona = this.personaRepository.save(persona);
		return persona;
	}

	private Persona updatePersona(Persona persona) {
		persona = this.personaRepository.save(persona);
		return persona;
	}

}
