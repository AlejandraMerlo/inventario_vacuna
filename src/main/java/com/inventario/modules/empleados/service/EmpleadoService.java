package com.inventario.modules.empleados.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.modules.dto.EmpleadoDto;
import com.inventario.modules.empleados.repository.EmpleadoRepository;
import com.inventario.modules.models.Empleado;
import com.inventario.modules.models.Persona;
import com.inventario.modules.models.TipoVacuna;
import com.inventario.modules.models.Usuario;
import com.inventario.modules.models.VacunaPersona;
import com.inventario.modules.personaVacunas.service.PersonaVacunaService;
import com.inventario.modules.personas.service.PersonaService;
import com.inventario.modules.usuarios.service.UsuarioService;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PersonaVacunaService personaVacunaService;

	public List<Empleado> getEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		empleados = this.empleadoRepository.getEmpleados();
		return empleados;
	}

	public Empleado getEmpleadoByCodigo(int codigoEmpleado) {
		Empleado empleado = new Empleado();
		empleado = this.empleadoRepository.getEmpleadoByCodigo(codigoEmpleado);
		return empleado;
	}

	public boolean validarExistenciaEmpleado(int codigoEmpleado) {
		boolean respuesta = this.getEmpleadoByCodigo(codigoEmpleado) != null;
		return respuesta;
	}

	public String getNombreEmpleado(int codigoEmpleado) {
		String nombreEmpleado = "";
		Empleado empleado = this.getEmpleadoByCodigo(codigoEmpleado);
		if (empleado != null) {
			nombreEmpleado = empleado.getPersona().getNombrePersona();
		}
		return nombreEmpleado;
	}

	public List<Empleado> getEmpleadosByEstadoVacuna(Boolean vacunado) {
		List<Empleado> empleados = new ArrayList<>();
		empleados = this.empleadoRepository.getEmpleadosByVacunacion(vacunado);
		return empleados;
	}

	public List<Empleado> getEmpleadosByTipoVacuna(TipoVacuna tipoVacuna) {
		List<Empleado> empleados = new ArrayList<>();
		List<Empleado> empleadosTipoVacuna = new ArrayList<>();
		empleados = this.empleadoRepository.getEmpleadosByVacunacion(true);
		for (Empleado empleado : empleados) {
			List<VacunaPersona> vacunas = this.personaVacunaService.getVacunasByPersonaTipoVacuna(
					empleado.getPersona().getCodigoPersona(), tipoVacuna.getCodigoTipo());
			if (!vacunas.isEmpty()) {
				empleadosTipoVacuna.add(empleado);
			}
		}

		return empleadosTipoVacuna;
	}

	public List<Empleado> getEmpleadosByFechasVacuna(EmpleadoDto empleadoDto) {
		List<Empleado> empleados = new ArrayList<>();
		List<Empleado> empleadosFechasVacuna = new ArrayList<>();
		empleados = this.empleadoRepository.getEmpleadosByVacunacion(true);
		for (Empleado empleado : empleados) {
			List<VacunaPersona> vacunas = this.personaVacunaService.getVacunasByFechaVacuna(
					empleado.getPersona().getCodigoPersona(), empleadoDto.getInicioVacunacion(),
					empleadoDto.getFinVacunacion());
			if (!vacunas.isEmpty()) {
				empleadosFechasVacuna.add(empleado);
			}
		}

		return empleadosFechasVacuna;
	}

	public Empleado createEmpleadoUsuario(EmpleadoDto empleadoDto) {
		Empleado empleado = this.insertEmpleado(empleadoDto);
		if (empleado != null) {
			this.usuarioService.insertUsuario(empleadoDto.getUsuario(), empleado);
		}
		return empleado;
	}

	public Empleado insertEmpleado(EmpleadoDto empleadoDto) {
		Empleado empleado = empleadoDto.getEmpleado();
		Persona persona = this.personaService.createPersona(empleadoDto.getPersona());
		if (persona != null) {
			empleado.setPersona(persona);
			empleado.setEstado(true);
			empleado.setFechaRegistro(new Date());
			empleado = this.saveEmpleado(empleado);
			return empleado;
		} else {
			return null;
		}
	}

	public void deleteEmpleado(Empleado empleado) {
		Persona persona = empleado.getPersona();
		List<Usuario> usuariosEmpleado = this.usuarioService.getUsuariosByEmpleado(empleado);
		this.personaService.deletePersona(persona);
		this.usuarioService.deleteUsuarios(usuariosEmpleado);
		this.empleadoRepository.delete(empleado);
	}

	public Empleado updateEmpleado(Empleado empleado) {
		empleado = this.update(empleado);
		return empleado;
	}

	private Empleado saveEmpleado(Empleado empleado) {
		empleado = this.empleadoRepository.save(empleado);
		return empleado;
	}

	private Empleado update(Empleado empleado) {
		empleado = this.empleadoRepository.save(empleado);
		return empleado;
	}

}
