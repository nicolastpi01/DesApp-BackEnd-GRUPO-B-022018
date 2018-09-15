package model;

import java.util.ArrayList;

public class Registro {
	ArrayList<Correo> correos = new ArrayList<Correo>(); // Correos de usuarios registrados

	public Boolean estaRegistrado(Usuario usuario) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean sePuedeRegistrar(Usuario usuario) {
		return !existeCorreoAsociado(usuario) && true;//tieneFormatoValido(usuario); // Falla formato valido (una boludes)
	}

	private boolean existeCorreoAsociado(Usuario usuario) {
		Boolean existeCorreo = false;
		for (int i=0; correos.size() < i; i++) { 
			existeCorreo = existeCorreo || correos.get(i).esIgual(usuario.getCorreo());
		}
		return existeCorreo;
	}

	// Falla
	private boolean tieneFormatoValido(Usuario usuario) {
		return usuario.estaBienFormado();
	}

	public void registrar(Usuario usuario) {
		correos.add(usuario.getCorreo());
	}

}
