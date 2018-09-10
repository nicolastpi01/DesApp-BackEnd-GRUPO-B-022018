package model;

import java.util.List;
import java.util.ArrayList;

public class Home {
	List<Subasta> subastas;
	List<Usuario> usuarios;
	
	public Home() {
		this.subastas = new ArrayList<Subasta>();
		this.usuarios = new ArrayList<Usuario>();
	}
}
