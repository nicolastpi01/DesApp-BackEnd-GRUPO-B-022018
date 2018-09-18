package model;

import java.util.ArrayList;
import java.util.List;

//LA SUBASTA NO PUEDE SER NUEVA (YA QUE NO ESTA PUBLICADA TODAVIA)
//LA SUBASTA NO PUEDE SER FINALIZADA (NI HABLAR)
//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY [Una forma de aplicar el filtro por reciente]
public class CriterioPorTerminar implements CriterioBusqueda {
	private static final CriterioPorTerminar instance = new CriterioPorTerminar();
	private static final int DiasCotaMaxReciente = 3;

	
	@Override
	public ArrayList<Subasta> buscar(List<Subasta> subastas) {
		ArrayList<Subasta> porTerminar = new ArrayList<Subasta>();
		for(int i=0; i < subastas.size(); i++) {
			if (estaPorTerminar(subastas.get(i))) porTerminar.add(subastas.get(i));
		}
		return porTerminar;
	}
	
	private boolean estaPorTerminar(Subasta subasta) {
		return subasta.estaEnProgreso() && subasta.finalizaDentroDe(DiasCotaMaxReciente);
	}
    
    //private constructor to avoid client applications to use constructor
    private CriterioPorTerminar(){}

    public static CriterioPorTerminar getInstance(){
        return instance;
    }

}
