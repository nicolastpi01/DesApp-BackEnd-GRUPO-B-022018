package model;

abstract class EstadoSubasta {
	
	public Boolean esNueva() {
		return false;
	}
	
	public Boolean esEnProgreso() {
		return false;
	}
	
	public Boolean estaTerminada() {
		return false;
	}
	
}
