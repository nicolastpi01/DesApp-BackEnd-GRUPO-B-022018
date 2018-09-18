package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CriterioPopular implements CriterioBusqueda {
	private static final CriterioPopular instance = new CriterioPopular();
	private final int MINIMO_DE_SUBASTAS_PERMITIDO = 5;
// LA FUNCION DE NRO ES QUE SI LA CANT. DE SUBASTAS ES DEMASIADO PEQUEÑA MUESTRE TODAS, Y NO APLIQUE EL FILTRO
	
	@Override
	// BORRAR EL COMENTARIO UNA VEZ QUE QUIERA AGREAGAR ESTE COMPORTAMIENTO
	public List<Subasta> buscar(List<Subasta> subastas) {
		/*ArrayList<Subasta> subastasPop = new ArrayList<Subasta>();
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
		*/
		Collections.sort(subastas,(sub1, sub2) ->  sub2.cantidadPostores() - sub1.cantidadPostores());
		 return subastas.stream().limit(MINIMO_DE_SUBASTAS_PERMITIDO).collect(Collectors.toList());
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
    

    public static CriterioPopular getInstance(){
        return instance;
    }

}
