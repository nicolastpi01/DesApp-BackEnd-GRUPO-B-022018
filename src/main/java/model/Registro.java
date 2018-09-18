package model;

import java.util.ArrayList;

import model.exceptions.CorreoYaRegistradoException;

public class Registro {
	// clave/valor
	// Necesito una est. de datos mas compleja que permita guardar mas datos de un usuario. Por. ej: nombre usuario, clave, y correo.
	ArrayList<Correo> correos = new ArrayList<Correo>(); // Correos de usuarios registrados

	public Boolean inicioSesion(Usuario usuario) {
		return true;
	}

	public boolean sePuedeRegistrar(Usuario usuario) {
		if (existeCorreoAsociado(usuario)) throw new CorreoYaRegistradoException();
		else return true;
	}

	private boolean existeCorreoAsociado(Usuario usuario) {
		Boolean existeCorreo = false;
		for (int i=0; correos.size() < i; i++) { 
			existeCorreo = existeCorreo || correos.get(i).esIgual(usuario.getCorreo());
		}
		return existeCorreo;
	}

	// mas complejo    dfsdgsfbfdbfb
	public void registrar(Usuario usuario) {
		correos.add(usuario.getCorreo());
	}

}
