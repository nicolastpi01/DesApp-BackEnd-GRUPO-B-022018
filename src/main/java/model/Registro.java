package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.exceptions.CorreoYaRegistradoException;

public class Registro {
	
	List <Usuario> usuariosRegistrados;
	
	public Registro(List<Usuario> usuarios) {
		this.usuariosRegistrados = usuarios;
	}

	public Boolean inicioSesion(Usuario usuario) {
		return true;
	}

	public boolean sePuedeRegistrar(Usuario usuario) {
		if (existeCorreoAsociado(usuario)) throw new CorreoYaRegistradoException();
		else return true;
	}

	public boolean existeCorreoAsociado(Usuario usuario) {
		List<String> correos = this.usuariosRegistrados.stream().map(u -> u.getNombreDeCorreo()).collect(Collectors.toList());
		return correos.contains(usuario.getNombreDeCorreo());
	}

	public void registrar(Usuario usuario) {
		this.usuariosRegistrados.add(usuario);
	}
}