package model;

import java.util.ArrayList;

// hacer esto un singleton object
public class CriterioPopular implements CriterioBusqueda {
	private static final CriterioPopular instance = new CriterioPopular();
	@Override
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		// TODO Auto-generated method stub
		return null;
	}
    
    //private constructor to avoid client applications to use constructor
    private CriterioPopular(){}

    public static CriterioPopular getInstance(){
        return instance;
    }

}
