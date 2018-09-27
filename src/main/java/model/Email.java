package model;


public class Email {
	String text;
	
	public Email(String text) {
		this.text = text;
	}

	public boolean isValid() {
		 String regex = "^.*\\@(gmail|hotmail)\\.\\bcom\\b";
		 return text.matches(regex);
		//return nombre.contains("@") && nombre.endsWith(".com"); // Una forma de validarlo (se puede validar de otra forma) --> contains(@Gmail)
	}

	public boolean isEqual(Email email) {
		return text.equals(email.getText());
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	

}
