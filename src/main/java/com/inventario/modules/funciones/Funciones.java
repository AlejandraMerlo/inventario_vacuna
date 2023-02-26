package com.inventario.modules.funciones;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Funciones {

	public static boolean emailValidator(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if (!validator.isValid(email)) {
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String s) {
	//	boolean isNumeric = (s != null && s.matches("[0-9]+"));
	//	System.out.println("IsNumeric: " + isNumeric);
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	public static boolean isAlpha(String s) {
		Pattern alphaPattern = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
		return alphaPattern.matcher(s).find();
	}

	public static String cadenaAleatoria(int longitud) {
		// El banco de caracteres
		String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		// La cadena en donde iremos agregando un carácter aleatorio
		String cadena = "";
		for (int x = 0; x < longitud; x++) {
			int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
			char caracterAleatorio = banco.charAt(indiceAleatorio);
			cadena += caracterAleatorio;
		}
		return cadena;
	}

	public static int numeroAleatorioEnRango(int minimo, int maximo) {
		// nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos
		// 1
		return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}
}
