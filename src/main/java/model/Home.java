package model;

import java.util.ArrayList;

public class Home {
	
	public ArrayList<Subasta> subastasPopulares(ArrayList<Subasta> subastas) {
		return CriterioPopular.getInstance().buscar(subastas);
	}
	
	public ArrayList<Subasta> subastasRecientes(ArrayList<Subasta> subastas) {
		return CriterioReciente.getInstance().buscar(subastas);
	}

	public ArrayList<Subasta> subastasPorTerminar(ArrayList<Subasta> subastas) {
		return CriterioPorTerminar.getInstance().buscar(subastas);
	}

	public ArrayList<Subasta> buscarPorDescripcion(String descripcion, ArrayList<Subasta> subastas) {
		return CriterioPorDescripcion.getInstance(descripcion).buscar(subastas);
	}
	
	public ArrayList<Subasta> buscarPorTitulo(String titulo, ArrayList<Subasta> subastas) {
		return CriterioPorTitulo.getInstance(titulo).buscar(subastas);
	}
	
}
