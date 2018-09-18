package model;


public class Correo {
	String nombre;
	
	public Correo(String nombre) {
		this.nombre = nombre;
	}

	public boolean esValido() {
		 String regex = "^.*\\@(gmail|hotmail)\\.\\bcom\\b";
		 return nombre.matches(regex);
		//return nombre.contains("@") && nombre.endsWith(".com"); // Una forma de validarlo (se puede validar de otra forma) --> contains(@Gmail)
	}

	public boolean esIgual(Correo correo) {
		return nombre.equals(correo.getNombre());
	}

	public String getNombre() {
		return this.nombre;
	}

}
