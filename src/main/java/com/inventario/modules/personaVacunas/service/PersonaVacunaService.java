package com.inventario.modules.personaVacunas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.VacunaPersona;
import com.inventario.modules.personaVacunas.repository.PersonaVacunaRepository;

@Service
public class PersonaVacunaService {

	@Autowired
	private PersonaVacunaRepository personaVacunaRepository;

	public List<VacunaPersona> getVacunasByPersona(int codigoPersona) {
		List<VacunaPersona> vacunas = new ArrayList<>();
		vacunas = this.personaVacunaRepository.getVacunasByPersona(codigoPersona);
		return vacunas;
	}

	public VacunaPersona createVacunaPersona(VacunaPersona vacuna) {
		vacuna = this.saveVacunaPersona(vacuna);
		return vacuna;
	}

	private VacunaPersona saveVacunaPersona(VacunaPersona vacunaPersona) {
		vacunaPersona = this.personaVacunaRepository.save(vacunaPersona);
		return vacunaPersona;
	}

	private VacunaPersona updateVacunaPersona(VacunaPersona vacunaPersona) {
		vacunaPersona = this.personaVacunaRepository.save(vacunaPersona);
		return vacunaPersona;
	}
}
