package com.inventario.modules.tipoVacunas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.TipoVacuna;

public interface TipoVacunaRepository extends JpaRepository<TipoVacuna, Integer> {

	@Query(value = "SELECT tipo FROM TipoVacuna tipo ORDER BY tipo.nombreVacuna ASC")
	public List<TipoVacuna> getTiposVacuna();

	@Query(value = "SELECT tipo FROM TipoVacuna tipo WHERE tipo.codigoTipo = :codigoTipo")
	public TipoVacuna getTipoVacunaByCodigo(@Param("codigoTipo") int codigoTipo);
}
