package com.inventario.modules.dto;

import java.util.Date;

import com.inventario.modules.models.Empleado;
import com.inventario.modules.models.Persona;
import com.inventario.modules.models.TipoVacuna;
import com.inventario.modules.models.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDto {

	private Empleado empleado;
	private Persona persona;
	private Usuario usuario;
	private Date inicioVacunacion;
	private Date finVacunacion;
	private TipoVacuna tipoVacuna;

}
