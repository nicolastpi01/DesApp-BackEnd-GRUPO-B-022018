package model;

import model.exceptions.TooLongNameException;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidPasswordException;

public class User {
	
	String name;
	String lastName;
	Email email;
	Pass password;
	Fecha birthday;
	Profile profile; // No es necesario el perfil Anonimo ya por ahora no tiene comportamiento, de modo que Usuario base seria anonimo
	// El que si tiene comportamiento es UsuarioRegistrado // mmmm, no
	
	
	public User(String name, String lastName, Email email, Pass password, Fecha birthday) {
		this.setName(name);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setBirthday(birthday);
		this.setProfile(new Registrado());
	}

	public User() {}
	
	private Boolean isTooLong(String name) {
		return name.length() > 30;
	}
	
	public String emailName(){
		return email.getText();
	}
	
	////////////////////////////////// GETTERS & SETTERS
	
	private void setLastName(String lastName) {
		if (! isTooLong(lastName)) this.lastName = lastName;
		else throw new TooLongNameException();
	}

	private void setName(String name) {
		if (! isTooLong(name)) this.name = name;
		else throw new TooLongNameException();
	}
	
	private void setPassword(Pass password) {
		if (password.isValid()) this.password = password;
		else throw new InvalidPasswordException();
		
	}

	private void setEmail(Email email) {
		if (email.isValid()) this.email = email;
		else throw new InvalidEmailException();
	}
	
	// temporally without verification
	private void setBirthday(Fecha birthday) {
		this.birthday = birthday;
	}

	public Email getEmail() {
		return this.email;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Profile getProfile() {
		return this.profile;
	}


	// a better name?
	public void makeAOfert(Subasta subasta) {
		// if soy el primer postor --> un comportamiento
		// sino otro
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
