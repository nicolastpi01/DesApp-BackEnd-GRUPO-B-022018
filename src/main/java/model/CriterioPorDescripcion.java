package model;

import java.util.ArrayList;

public class CriterioPorDescripcion implements CriterioBusqueda {
	private static final CriterioPorDescripcion instance = new CriterioPorDescripcion();
	private String descripcion;
	
	@Override
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		// TODO Auto-generated method stub
		getDescripcion();
		return null;
	}
	
    //private constructor to avoid client applications to use constructor
    private CriterioPorDescripcion(){}

    public static CriterioPorDescripcion getInstance(String descripcion){
    	instance.setDescripcion(descripcion);
        return instance;
    }

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	private String getDescripcion() {
		return this.descripcion;
	}

}
