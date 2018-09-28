package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.exceptions.CorreoYaRegistradoException;

public class Registry {
	
	List <User> usuariosRegistrados;
	
	public Registry(List<User> usuarios) {
		this.usuariosRegistrados = usuarios;
	}

	public Boolean inicioSesion(User usuario) {
		return true;
	}

	public boolean sePuedeRegistrar(User usuario) {
		if (existeCorreoAsociado(usuario)) throw new CorreoYaRegistradoException();
		else return true;
	}

	public boolean existeCorreoAsociado(User usuario) {
		List<String> correos = this.usuariosRegistrados.stream().map(u -> u.emailName()).collect(Collectors.toList());
		return correos.contains(usuario.emailName());
	}

	public void registrar(User usuario) {
		this.usuariosRegistrados.add(usuario);
	}
}