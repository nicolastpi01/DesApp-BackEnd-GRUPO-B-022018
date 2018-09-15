package model;

public class Usuario {
	
	String nombre;
	String apellido;
	Correo correo;
	Pass password;
	FechaNac fechaNacimiento;
	//Perfil perfil;
	
	
	public Usuario(String nombre, String apellido, Correo correo, Pass password, FechaNac fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		//this.perfil = new Anonimo();
	}
	

	public Boolean estaBienFormado() {
		return tieneLongValida(nombre) && tieneLongValida(apellido) && correo.esValido() && 
				password.esValido();
	}
	
	private Boolean tieneLongValida(String dato) {
		return dato.length() <= 30;
	}
	

	public Correo getCorreo() {
		return this.correo;
	}
	
	// No deberia ser un metodo publico
	//public void setPerfil(Perfil perfil) {
	//	this.perfil = perfil;
	//}
	
	/*
	public void publicarSubasta(Subasta subasta) {
		subasta.setEstado_subasta(EstadoSubasta.EN_PROGRESO);
	}
	
	//public void ofertarEnSubasta(Integer monto, Subasta subasta) {
	//	
	//}
	 * 
	*/
}
