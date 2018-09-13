package model;

import java.util.ArrayList;

public class CriterioPorTitulo implements CriterioBusqueda {
	private static final CriterioPorTitulo instance = new CriterioPorTitulo();
	private String titulo;

	@Override
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		// TODO Auto-generated method stub
		getTitulo(); // se usa acá
		return null;
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
	
	private String getTitulo() {
		return this.titulo;
	}

}
