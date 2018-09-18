package model;

import java.util.ArrayList;
import java.util.List;

// LA SUBASTA NO PUEDE SER NUEVA (YA QUE NO ESTA PUBLICADA TODAVIA)
// LA SUBASTA NO PUEDE SER FINALIZADA (NI HABLAR)
// LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY [Una forma de aplicar el filtro por reciente]
public class CriterioReciente implements CriterioBusqueda {
	private static final CriterioReciente instance = new CriterioReciente();
	private static final int DiasCotaMaxReciente = 3;

	@Override
	public ArrayList<Subasta> buscar(List<Subasta> subastas) {
		ArrayList<Subasta> recientes = new ArrayList<Subasta>();
		for(int i=0; i < subastas.size(); i++) {
			if (esReciente(subastas.get(i))) recientes.add(subastas.get(i));
		}
		return recientes;
	}
	
	private Boolean esReciente(Subasta subasta) {
		return subasta.estaEnProgreso() && subasta.fuePublicadaHace(DiasCotaMaxReciente); 
	}
	
	//private constructor to avoid client applications to use constructor
    private CriterioReciente(){}

    public static CriterioReciente getInstance(){
        return instance;
    }

}
