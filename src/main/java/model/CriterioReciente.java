package model;

import java.util.ArrayList;

public class CriterioReciente implements CriterioBusqueda {
	private static final CriterioReciente instance = new CriterioReciente();

	@Override
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//private constructor to avoid client applications to use constructor
    private CriterioReciente(){}

    public static CriterioReciente getInstance(){
        return instance;
    }

}
