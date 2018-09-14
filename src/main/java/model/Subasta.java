package model;

import org.joda.time.LocalTime;
import org.joda.time.LocalDate;

public class Subasta {
	String titulo;
	String descripcion;
	String direccion;
	String urlFoto;
	int precioInicial;
	//int precioActual; ?
	LocalDate fechaPublicacion;
	LocalDate fechaFinalizacion;
	LocalTime horaFinalizacion;
	EstadoSubasta estado;
	Usuario propietario; // Por ahora acá
	
	public void setEstado(EstadoSubasta estado) {
		this.estado = estado;
	}
	
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public boolean esNueva() {
		return estado.esNueva();
	}

	public Usuario getPropietario() {
		return this.propietario;
	}

	public boolean estaEnProgreso() {
		return estado.esEnProgreso();
	}

	public boolean estaEnProgresoPara(Usuario propietario) {
		return estado.esEnProgreso() && perteneceA(propietario);
	}

	private boolean perteneceA(Usuario usuario) {
		return propietario.equals(usuario);
	}

	
	
	
}
