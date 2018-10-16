package model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
// Clase sujeta a cambio de nombre
public class TooLongNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TooLongNameException() {
		super("nombre demasiado extenso 'cantidad de caracteres maximos 30'");
	}

}
