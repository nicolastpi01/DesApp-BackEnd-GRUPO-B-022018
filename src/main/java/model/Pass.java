package model;

public class Pass {
	String text;
	
	public Pass(String text) {
		this.text = text;
	}

	// hay que verificar que contenga nros y Mayusculas (no sé como hacerlo)
	public boolean isValid() {
		return text.length() >= 4 && text.length() <= 20 && true;  
	}

}
