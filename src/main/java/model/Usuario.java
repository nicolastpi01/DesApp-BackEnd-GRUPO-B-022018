package model;
import org.joda.time.LocalDate;
import java.util.Date;

public class Usuario {
	
	String nombre;
	String apellido;
	String email;
	String contrasena;
	LocalDate fechaDeNac;
	
	public void ofertarEnSubasta(Integer monto, Subasta subasta) {
		
	}
	
	/*
	public void publicarSubasta(Subasta subasta) {
		subasta.setEstado_subasta(EstadoSubasta.EN_PROGRESO);
	}
	*/
}
