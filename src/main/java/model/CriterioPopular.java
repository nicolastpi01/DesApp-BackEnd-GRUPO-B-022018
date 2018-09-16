package model;

import java.util.ArrayList;

public class CriterioPopular implements CriterioBusqueda {
	private static final CriterioPopular instance = new CriterioPopular();
	private int minimoDeSubastasPermitido = 5;
// LA FUNCION DE NRO ES QUE SI LA CANT. DE SUBASTAS ES DEMASIADO PEQUEÑA MUESTRE TODAS, Y NO APLIQUE EL FILTRO
	
	@Override
	// BORRAR EL COMENTARIO UNA VEZ QUE QUIERA AGREAGAR ESTE COMPORTAMIENTO
	public ArrayList<Subasta> buscar(ArrayList<Subasta> subastas) {
		ArrayList<Subasta> subastasPop = new ArrayList<Subasta>();
		if (false) { //(subastas.size() <= minimoDeSubastasPermitido) {
			subastasPop = subastas;
		}
		else {
			int media = mediaPostoresPorSubasta(subastas);
			for (int i=0; i < subastas.size(); i++) {
				if (subastas.get(i).esPopular(media)) subastasPop.add(subastas.get(i));
			}		
		}
		return subastasPop;
	}
	
	private int mediaPostoresPorSubasta(ArrayList<Subasta> subastas) {
		int totalPostores = 0;
		int totalSubastas = subastas.size();
		for(int i=0; i < subastas.size(); i++) {
			totalPostores += subastas.get(i).cantidadPostores();
		}
		System.out.println(totalPostores % totalSubastas);
		return totalPostores % totalSubastas;
	}
    
    //private constructor to avoid client applications to use constructor
    private CriterioPopular(){}

    public static CriterioPopular getInstance(){
        return instance;
    }

}
