package model;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.DescripcionLongitudMin10Max100Exception;
import model.exceptions.FechaFinalizacionNoEsComoMinimoDosDiasMayorALaFechaPublicacion;
import model.exceptions.HoraInvalidaException;
import model.exceptions.LaFechaDePublicacionDebeSerMayorAFechaActual;
import model.exceptions.NoSePuedeEliminarUnaSubastaConPostoresException;
import model.exceptions.PropietarioParticipaComoPujanteEnSuPropiaSubastaException;
import model.exceptions.TituloLongitudMin10Max50Exception;

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
	Usuario propietario;
	List<Usuario> postores = new ArrayList<Usuario>();
	
	// Por cuestiones de simplicidad en test
	public Subasta() {} 
	
	public Subasta(String titulo, String descripcion, String direccion, int precioInicial,
			Fecha fechaPublicacion, Fecha fechaFinalizacion, int horaFinalizacion) {
		
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
		this.setDireccion(direccion);
		this.setPrecioInicial(precioInicial);
		this.setFechaPublicacion(fechaPublicacion);
		this.setFechaFinalizacion(fechaFinalizacion);
		this.setHoraFinalizacion(horaFinalizacion);
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

	public boolean tieneMismoTitulo(String titulo) {
		return getTitulo().equals(titulo);
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

	public boolean finalizaDentroDe(int cantDias) {
		return getFechaFinalizacion().sucedesDentroDe(cantDias);
	}

	public void agregarPostor(Usuario usuarioPostor) {
		if (perteneceA(usuarioPostor)) {
			throw new PropietarioParticipaComoPujanteEnSuPropiaSubastaException();
		}
		else {
			this.postores.add(usuarioPostor);
		}
	}

	public boolean esPopular(int mediaPostoresPorSubasta) {
		return getPostores().size() >= mediaPostoresPorSubasta;
	}
	
	public int cantidadPostores() {
		return getPostores().size();
	}
	
	private Boolean tieneLongTituloValido(String titulo) {
		int longTitulo = titulo.length(); 
		return longTitulo >= 10 && longTitulo <= 30;
	}
	
	private Boolean tieneLongDescripcionValida(String descripcion) {
		int longDescr = descripcion.length();
		return longDescr >= 10 && longDescr <= 100;
	}
	
	/////////////////////////////////// SETTERS && GETTERS   ////////////////////////////////////////////////
	
	private void setDireccion(String direccion) {
		this.direccion = direccion;	
	}
	
	private Fecha getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}
	
	
	public void setFechaPublicacion(Fecha fechaPublicacion) {
		if(fechaPublicacion.esMayorAHoy()) this.fechaPublicacion = fechaPublicacion;
		else throw new LaFechaDePublicacionDebeSerMayorAFechaActual();		
	}
	
	// requiere verificacion // No terminado
	public void setFechaFinalizacion(Fecha fechaFinalizacion) {
		if (fechaFinalizacion.esMayorPorAlMenosDosDiasALaFechaPublicacion(getFechaPublicacion())) {
			this.fechaFinalizacion = fechaFinalizacion;	
		}
		else throw new FechaFinalizacionNoEsComoMinimoDosDiasMayorALaFechaPublicacion();
	}
	
	private Fecha getFechaPublicacion() {
		return this.fechaPublicacion;
	}
	
	public void setEstado(EstadoSubasta estado) {
		this.estado = estado;
	}
	
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	
	public Usuario getPropietario() {
		return this.propietario;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		if (this.tieneLongTituloValido(titulo)) this.titulo = titulo;
		else throw new TituloLongitudMin10Max50Exception();
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		if (this.tieneLongDescripcionValida(descripcion)) this.descripcion = descripcion;
		else throw new DescripcionLongitudMin10Max100Exception();
	}
	
	public List<Usuario> getPostores() {
		return this.postores;
	}
	
	private void setPrecioInicial(int precioInicial) {
		this.precioInicial = precioInicial;
	}
	
	private void setHoraFinalizacion(int horaFinalizacion) {
		if (horaFinalizacion >= 0 && horaFinalizacion <= 23) this.horaFinalizacion = horaFinalizacion;
		else throw new HoraInvalidaException();
	}

	public boolean sePuedeEliminar() {
		if (getPostores().size() == 0) return true;
		else throw new NoSePuedeEliminarUnaSubastaConPostoresException();
	}

	public boolean tieneComoPostor(Usuario usuario) {
		Boolean esPostor = false;
		for(int i=0; i < postores.size(); i++) {
			esPostor = esPostor || postores.get(i).equals(usuario);
		}
		return esPostor;
	}
	
	////////
	
	public void setPostores(List<Usuario> postores2) {
		this.postores = postores2;
	}

}
