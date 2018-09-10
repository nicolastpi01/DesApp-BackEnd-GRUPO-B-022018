package model;

import org.joda.time.LocalTime;
import org.joda.time.LocalDate;

public class Subasta {
	String titulo;
	String descripcion;
	String direccion;
	String urlFotoElemento;
	int precioInicial;
	int precioActual;
	LocalDate fecha_publicacion;
	LocalDate fecha_finalizacion;
	LocalTime hora_finalizacion;
	EstadoSubasta estado_subasta;
	
	public Subasta(	String titulo, String descr, String dir, String url, int precioInicial,
					LocalDate fecha_pub,LocalDate fecha_fin, LocalTime hora_fin) {
		this.titulo = titulo;
		this.descripcion = descr;
		this.direccion = dir;
		this.urlFotoElemento = url;
		this.precioInicial = precioInicial;
		this.precioActual = precioInicial;
		this.fecha_publicacion = fecha_pub;
		this.fecha_finalizacion = fecha_fin;
		this.hora_finalizacion = hora_fin;
		this.estado_subasta = EstadoSubasta.NUEVA;
	}
	
	
	
	//////////G AND S//////////////////

	public EstadoSubasta getEstado_subasta() {
		return estado_subasta;
	}

	public void setEstado_subasta(EstadoSubasta estado_subasta) {
		this.estado_subasta = estado_subasta;
	}

}
