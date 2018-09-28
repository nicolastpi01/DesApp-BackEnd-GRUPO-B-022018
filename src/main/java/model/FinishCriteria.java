package model;

import java.util.ArrayList;
import java.util.List;

//LA SUBASTA NO PUEDE SER NUEVA (YA QUE NO ESTA PUBLICADA TODAVIA)
//LA SUBASTA NO PUEDE SER FINALIZADA (NI HABLAR)
//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY [Una forma de aplicar el filtro por reciente]
public class FinishCriteria implements SearchCriteria {
	private static final FinishCriteria instance = new FinishCriteria();
	private static final int maxDaysRecent = 3;

	
	@Override
	public ArrayList<Auction> search(List<Auction> auctions) {
		ArrayList<Auction> nextToFinish = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if (auctions.get(i).isNextToFinish(maxDaysRecent)) nextToFinish.add(auctions.get(i));
		}
		return nextToFinish;
	}
    
    //private constructor to avoid client applications to use constructor
    private FinishCriteria(){}

    public static FinishCriteria getInstance(){
        return instance;
    }

}
