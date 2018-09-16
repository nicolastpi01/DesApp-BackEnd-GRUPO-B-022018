package model;

import org.joda.time.DateTime;
import org.joda.time.Days;

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
	// Esto Funciona, jajajajajaajaja
	public boolean sucedisteHace(int cantDias) {
		DateTime miFecha = new DateTime(anio,mes,dia,0,0,0);
		DateTime hoy = DateTime.now();
		int diferenciaEnDias = Days.daysBetween(miFecha.toLocalDate(), hoy.toLocalDate()).getDays();
		return  diferenciaEnDias <= cantDias;
	}

	public boolean sucedesDentroDe(int cantDias) {
		DateTime miFecha = new DateTime(anio,mes,dia,0,0,0);
		DateTime hoy = DateTime.now();
		int diferenciaEnDias = Days.daysBetween(miFecha.toLocalDate(), hoy.toLocalDate()).getDays();
		return  diferenciaEnDias <= cantDias;
	}

}
