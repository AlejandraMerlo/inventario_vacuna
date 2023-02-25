package com.inventario.modules.tipoVacunas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.models.TipoVacuna;
import com.inventario.modules.tipoVacunas.repository.TipoVacunaRepository;

@Service
public class TipoVacunaService {

	@Autowired
	private TipoVacunaRepository tipoVacunaRepository;

	public List<TipoVacuna> getTiposVacunas() {
		List<TipoVacuna> tiposVacuna = new ArrayList<>();
		tiposVacuna = this.tipoVacunaRepository.getTiposVacuna();
		return tiposVacuna;
	}

	public TipoVacuna getTipoVacunaByCodigo(int codigoTipo) {
		TipoVacuna tipoVacuna = new TipoVacuna();
		tipoVacuna = this.tipoVacunaRepository.getTipoVacunaByCodigo(codigoTipo);
		return tipoVacuna;
	}

	private TipoVacuna updateTipoVacuna(TipoVacuna tipoVacuna) {
		tipoVacuna = this.tipoVacunaRepository.save(tipoVacuna);
		return tipoVacuna;
	}
}
