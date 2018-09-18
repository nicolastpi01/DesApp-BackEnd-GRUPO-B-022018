
package model;

import java.util.ArrayList;
import java.util.List;

public class CriterioPorDescripcion implements CriterioBusqueda {
	private static final CriterioPorDescripcion instance = new CriterioPorDescripcion();
	private String descripcion;
	
	@Override
	public ArrayList<Subasta> buscar(List<Subasta> subastas) {
		ArrayList<Subasta> subastasConDesc = new ArrayList<Subasta>();
		for (int i=0; i < subastas.size(); i++) {
			if (subastas.get(i).tieneDescripcion(getDescripcion())) subastasConDesc.add(subastas.get(i));
		}
		return subastasConDesc;
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
