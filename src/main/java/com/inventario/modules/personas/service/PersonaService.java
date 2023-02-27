package com.inventario.modules.personas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.funciones.Funciones;
import com.inventario.modules.models.Persona;
import com.inventario.modules.models.VacunaPersona;
import com.inventario.modules.personaVacunas.service.PersonaVacunaService;
import com.inventario.modules.personas.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private PersonaVacunaService personaVacunaService;

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

	public Persona createPersona(Persona persona) {
		if (Funciones.isNumeric(persona.getCedula().trim()) && this.nombreValido(persona)
				&& Funciones.emailValidator(persona.getCorreo().trim())) {
			persona.setFechaRegistro(new Date());
			persona.setVacunado(false);
			persona = this.savePersona(persona);
			return persona;
		} else {
			return null;
		}
	}

	public void deletePersona(Persona persona) {
		this.personaRepository.delete(persona);
	}

	public Persona updatePersona(Persona persona) throws Exception {
		List<VacunaPersona> vacunasPersona = new ArrayList<>();
		if (!Funciones.isNumeric(persona.getCedula().trim()) || !this.nombreValido(persona)
				|| !Funciones.emailValidator(persona.getCorreo().trim()))
			throw new Exception("Los datos no se han podido validar.");

		vacunasPersona = persona.getVacunaPersonas();
		if (persona.getVacunado()) {
			vacunasPersona = this.personaVacunaService.insert(vacunasPersona);
		} else {
			if (vacunasPersona != null) {
				if (!vacunasPersona.isEmpty()) {
					this.personaVacunaService.deleteVacunasPersona(vacunasPersona);
				}
			}
		}
		persona.setVacunaPersonas(vacunasPersona);
		persona = this.update(persona);

		return persona;
	}

	private Persona savePersona(Persona persona) {
		persona = this.personaRepository.save(persona);
		return persona;
	}

	private Persona update(Persona persona) {
		persona = this.personaRepository.save(persona);
		return persona;
	}

	private boolean nombreValido(Persona persona) {
		Boolean respuesta = true;
		if (!Funciones.isAlpha(persona.getApellidos().trim()) || !Funciones.isAlpha(persona.getNombres().trim())) {
			respuesta = false;
		}
		return respuesta;
	}

}
