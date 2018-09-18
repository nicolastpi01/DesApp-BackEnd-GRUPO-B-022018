package model;

import java.util.ArrayList;
import java.util.List;

public class CriterioPorTitulo implements CriterioBusqueda {
	private static final CriterioPorTitulo instance = new CriterioPorTitulo();
	String titulo;

	@Override
	public ArrayList<Subasta> buscar(List<Subasta> subastas) {
		ArrayList<Subasta> subastasResultado = new ArrayList<Subasta>();
		for(int i=0; i < subastas.size(); i++) {
			if(subastas.get(i).tieneTitulo(getTitulo())) { 
				subastasResultado.add(subastas.get(i));	
			}
		}
		return subastasResultado;
	}
	
    //private constructor to avoid client applications to use constructor
    private CriterioPorTitulo(){}

    public static CriterioPorTitulo getInstance(String titulo){
    	instance.setTitulo(titulo);
        return instance;
    }

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

}
