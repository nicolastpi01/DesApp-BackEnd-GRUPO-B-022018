package model;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Fecha {
	int dia;
	int mes;
	int anio;
	
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	// Se deberia poder mejorar un poco este metodo (A mí me seco el cerebro)
	public boolean sucedisteHace(int cantDias) {
		LocalDate miFecha = new LocalDate(anio,mes,dia);
		LocalDate hoy = LocalDate.now();
		int diferenciaEnDias = Days.daysBetween(miFecha, hoy).getDays();
		return  diferenciaEnDias <= cantDias;
	}

	public boolean sucedesDentroDe(int cantDias) {
		DateTime miFecha = new DateTime(anio,mes,dia,0,0,0);
		DateTime hoy = DateTime.now();
		int diferenciaEnDias = Days.daysBetween(miFecha.toLocalDate(), hoy.toLocalDate()).getDays();
		return  diferenciaEnDias <= cantDias;
	}

	public boolean esMayorAHoy() {
		DateTime miFecha = new DateTime(anio,mes,dia,0,0,0);
		return miFecha.isAfterNow();
	}

	public boolean esMayorPorAlMenosDosDiasALaFechaPublicacion(Fecha fechaPublicacion) {	
		DateTime miFecha = new DateTime(getAnio(),getMes(),getDia(),0,0,0);
		int dia = fechaPublicacion.getDia();
		int mes = fechaPublicacion.getMes();
		int anio = fechaPublicacion.getAnio();
		DateTime publicacionFecha = new DateTime(anio,mes,dia,0,0,0);
		return true; // Esto no es así --> Comparar publicacionFecha con miFecha
	}
	
	/////////////////////////////// GETTERS && SETTERS  /////////////////////////////////////////////////////
	
	
	public int getDia() {
		return this.dia;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public int getAnio() {
		return this.anio;
	}

}
