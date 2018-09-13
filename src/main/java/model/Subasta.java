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
	EstadoSubasta estadoSubasta;
	
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
	
	
}
