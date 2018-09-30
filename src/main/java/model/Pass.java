package model;

public class Pass {
	String text;
	
	public Pass(String text) {
		this.text = text;
	}

	// hay que verificar que contenga nros y Mayusculas (no sé como hacerlo)
	public boolean isValid() {
		String regexCapitalLetter = "^.*[A-Z].*$";
		String regexNumber = "^.*[0-9].*$";
		return text.matches(regexCapitalLetter) && text.matches(regexNumber) && 
			   text.length() >= 4 && text.length() <= 20;
		
	}

}
