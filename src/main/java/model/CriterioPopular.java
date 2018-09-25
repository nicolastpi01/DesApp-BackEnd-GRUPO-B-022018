package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CriterioPopular implements CriterioBusqueda {
	private static final CriterioPopular instance = new CriterioPopular();
	private final int MINIMO_DE_SUBASTAS_PERMITIDO = 5;
	
	@Override
	public List<Subasta> buscar(List<Subasta> subastas) {
		Collections.sort(subastas,(sub1, sub2) ->  sub2.cantidadPostores() - sub1.cantidadPostores());
		return subastas.stream().limit(MINIMO_DE_SUBASTAS_PERMITIDO).collect(Collectors.toList());
	}
	
	public double mediaPostoresPorSubasta(List<Subasta> subastas) {
        return subastas.stream().mapToDouble(sub ->  sub.postores.size()).average().getAsDouble();    
	}
	
    public static CriterioPopular getInstance(){
        return instance;
    }

}
