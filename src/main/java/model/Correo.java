package model;

public class Correo {
	String nombre;
	
	public Correo(String nombre) {
		this.nombre = nombre;
	}

	public boolean esValido() {
		return nombre.contains("@") && nombre.endsWith(".com"); // Una forma de validarlo (se puede validar de otra forma) --> Contains(@) o contains(@Gmail)
	}

	public boolean esIgual(Correo correo) {
		return nombre.equals(correo.getNombre());
	}

	private String getNombre() {
		return this.nombre;
	}

}
