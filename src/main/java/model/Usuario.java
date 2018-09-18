package model;

import model.exceptions.ApellidoDemasiadoExtensoException;
import model.exceptions.FormatoNoValidoDeCorreoException;
import model.exceptions.NombreDemasiadoExtensoException;
import model.exceptions.PasswordDebeContenerMin4Max10AlfanumericoException;

public class Usuario {
	
	String nombre;
	String apellido;
	Correo correo;
	Pass password;
	Fecha fechaNacimiento;
	Perfil perfil; // No es necesario el perfil Anonimo ya por ahora no tiene comportamiento, de modo que Usuario base seria anonimo
	// El que si tiene comportamiento es UsuarioRegistrado // mmmm, no
	
	
	public Usuario(String nombre, String apellido, Correo correo, Pass password, Fecha fechaNacimiento) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCorreo(correo);
		this.setPassword(password);
		this.setFechaNacimiento(fechaNacimiento);
		this.setPerfil(new Registrado());
	}

	public Usuario() {}
	
	private Boolean tieneLongValida(String dato) {
		return dato.length() <= 30;
	}
	
	public String getNombreDeCorreo(){
		return correo.getNombre();
	}
	
	// GETTERS & SETTERS
	
	private void setApellido(String apellido) {
		if (tieneLongValida(apellido)) this.apellido = apellido;
		else throw new ApellidoDemasiadoExtensoException();
	}

	private void setNombre(String nombre) {
		if (tieneLongValida(nombre)) this.nombre = nombre;
		else throw new NombreDemasiadoExtensoException();
	}
	
	private void setPassword(Pass password) {
		if (password.esValido()) this.password = password;
		else throw new PasswordDebeContenerMin4Max10AlfanumericoException();
		
	}

	private void setCorreo(Correo correo) {
		if (correo.esValido()) this.correo = correo;
		else throw new FormatoNoValidoDeCorreoException();
	}
	
	// No requiere verificacion por ahora
	private void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Correo getCorreo() {
		return this.correo;
	}
	
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	
	//////////////////////////////////// CRUD GENERACION DE SUBASTAS  ///////////////////////////////////////////
	
	 //CREAR
	
	// NO VA ACÁ, MUY DIFICIL
	 
	 /*
	 PUJAR
	 MODIFICAR
	 ELIMINAR
	 
	*/
}
