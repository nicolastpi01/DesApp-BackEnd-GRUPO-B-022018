package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Clase sujeta a cambio de nombre (o a fusionarla con la excepcion de nombre demasiado extenso)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooLongLastNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TooLongLastNameException() {
		super("apellido demasiado extenso 'cantidad de caracteres maximos 30'");
	}

}
