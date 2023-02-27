package com.inventario.modules.personaVacunas.service;

import java.util.ArrayList;
import java.util.Date;
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

	public List<VacunaPersona> getVacunasByPersonaTipoVacuna(int codigoPersona, int codigoTipo) {
		List<VacunaPersona> vacunas = new ArrayList<>();
		vacunas = this.personaVacunaRepository.getVacunasByPersonaTipoVacuna(codigoPersona, codigoTipo);
		return vacunas;
	}

	public List<VacunaPersona> getVacunasByFechaVacuna(int codigoPersona, Date fechaInicio, Date fechaFin) {
		List<VacunaPersona> vacunas = new ArrayList<>();
		vacunas = this.personaVacunaRepository.getVacunasByPersonaFechaVacuna(codigoPersona, fechaInicio, fechaFin);
		return vacunas;
	}

	public VacunaPersona createVacunaPersona(VacunaPersona vacuna) {
		vacuna = this.saveVacunaPersona(vacuna);
		return vacuna;
	}

	public List<VacunaPersona> insert(List<VacunaPersona> vacunasPersona) {
		vacunasPersona = this.personaVacunaRepository.saveAll(vacunasPersona);
		return vacunasPersona;
	}

	public void deleteVacunasPersona(List<VacunaPersona> vacunasPersona) {
		for (VacunaPersona vacunaPersona : vacunasPersona) {
			this.personaVacunaRepository.delete(vacunaPersona);
		}
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
