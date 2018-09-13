package model;

import java.util.ArrayList;

public class Home {
	ArrayList<Subasta> subastas = new ArrayList<Subasta>(); // No es claro de donde salen las subastas, no son de la Home, esta solo las manipula (filtra)
	
	
	public void agregar(Subasta subasta) {
		subastas.add(subasta);
	}
	
	public ArrayList<Subasta> subastasPopulares() {
		return CriterioPopular.getInstance().buscar(subastas);
	}

	public ArrayList<Subasta> subastasRecientes() {
		return CriterioReciente.getInstance().buscar(subastas);
	}

	public ArrayList<Subasta> subastasPorTerminar() {
		return CriterioPorTerminar.getInstance().buscar(subastas);
	}

	public ArrayList<Subasta> buscarPorTitulo(String titulo) {
		return CriterioPorTitulo.getInstance(titulo).buscar(subastas);
	}

	public ArrayList<Subasta> buscarPorDescripcion(String descripcion) {
		return CriterioPorDescripcion.getInstance(descripcion).buscar(subastas);
	}
	
	
}
