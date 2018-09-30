package model;

import java.util.List;
import java.util.stream.Collectors;

//LA SUBASTA NO PUEDE SER NUEVA (YA QUE NO ESTA PUBLICADA TODAVIA)
//LA SUBASTA NO PUEDE SER FINALIZADA (NI HABLAR)
//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY [Una forma de aplicar el filtro por reciente]
public class FinishCriteria implements SearchCriteria {
	private static final FinishCriteria instance = new FinishCriteria();
	private static final int maxDaysRecent = 4;

	
	@Override
	public List<Auction> search(List<Auction> auctions) {
		return auctions.stream().filter(auc -> auc.isNextToFinish(maxDaysRecent)).collect(Collectors.toList());
	}
    
    //private constructor to avoid client applications to use constructor
    private FinishCriteria(){}

    public static FinishCriteria getInstance(){
        return instance;
    }

}
