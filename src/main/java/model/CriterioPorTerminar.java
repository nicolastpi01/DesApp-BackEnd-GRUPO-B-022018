package model;

import java.util.ArrayList;

public class CriterioPorTerminar implements CriterioBusqueda {
	private static final CriterioPorTerminar instance = new CriterioPorTerminar();

	
	@Override
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		// TODO Auto-generated method stub
		return null;
	}
    
    //private constructor to avoid client applications to use constructor
    private CriterioPorTerminar(){}

    public static CriterioPorTerminar getInstance(){
        return instance;
    }

}
