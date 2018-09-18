package model;

import java.util.ArrayList;
import java.util.List;

public class Home {
	
	public List<Subasta> subastasRecientes(List<Subasta> subastas) {
		return CriterioReciente.getInstance().buscar(subastas);
	}

	public List<Subasta> subastasPorTerminar(List<Subasta> subastas) {
		return CriterioPorTerminar.getInstance().buscar(subastas);
	}

	public List<Subasta> buscarPorDescripcion(String descripcion, ArrayList<Subasta> subastas) {
		return CriterioPorDescripcion.getInstance(descripcion).buscar(subastas);
	}
	
	public List<Subasta> buscarPorTitulo(String titulo, ArrayList<Subasta> subastas) {
		return CriterioPorTitulo.getInstance(titulo).buscar(subastas);
	}

	public List<Subasta> subastasPopulares(List<Subasta> subastas) {
		return CriterioPopular.getInstance().buscar(subastas);
	}
	
}
