package model;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.LocalDate; // Se va a usar en la comprobación de validez de una subasta


public class Subasta {
	String titulo;
	String descripcion;
	String direccion;
	ArrayList<String> urlFotos = new ArrayList<String>();
	int precioInicial;
	Fecha fechaPublicacion;
	Fecha fechaFinalizacion;
	int horaFinalizacion;
	EstadoSubasta estado = new NuevaSubasta();
	Usuario propietario; // Por ahora acá
	
	public Subasta() {} // Por cuestiones de simplicidad en test
	
	public Subasta(String titulo, String descripcion, String direccion, int precioInicial,
			Fecha fechaPublicacion, Fecha fechaFinalizacion, int horaFinalizacion) {
		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.precioInicial = precioInicial;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.horaFinalizacion = horaFinalizacion;
	}
	
	public Boolean esValida() {
		return true; // retorna un bool indicando si la subasta esta bien formada (fechaPublicacion correcta, hora correcta, etc)
	}
	
	public void setEstado(EstadoSubasta estado) {
		this.estado = estado;
	}
	
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	

	public boolean esNueva() {
		return estado.esNueva();
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
	
	public void agregarUnaFoto(String url) {
		urlFotos.add(url);
	}

	public Usuario getPropietario() {
		return this.propietario;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

	public boolean tieneMismoTitulo(String titulo) {
		return getTitulo().equals(titulo);
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean tieneDescripcion(String descripcion) {
		return getDescripcion().equals(descripcion);
	}

	public boolean tieneTitulo(String titulo) {
		return getTitulo().equals(titulo);
	}

	public boolean fuePublicadaHace(int cantDias) {
		return getFechaPublicacion().sucedisteHace(cantDias);
	}

	private Fecha getFechaPublicacion() {
		// TODO Auto-generated method stub
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Fecha fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public void setFechaFinalizacion(Fecha fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;	
	}

	public boolean finalizaDentroDe(int cantDias) {
		return getFechaFinalizacion().sucedesDentroDe(cantDias);
	}

	private Fecha getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

}
