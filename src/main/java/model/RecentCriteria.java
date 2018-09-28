package model;

import java.util.ArrayList;
import java.util.List;

// LA SUBASTA NO PUEDE SER NUEVA (YA QUE NO ESTA PUBLICADA TODAVIA)
// LA SUBASTA NO PUEDE SER FINALIZADA (NI HABLAR)
// LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY [Una forma de aplicar el filtro por reciente]
public class RecentCriteria implements SearchCriteria {
	private static final RecentCriteria instance = new RecentCriteria();
	private static final int DiasCotaMaxReciente = 3;

	@Override
	public ArrayList<Auction> search(List<Auction> auctions) {
		ArrayList<Auction> recents = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if (auctions.get(i).isRecent(DiasCotaMaxReciente)) recents.add(auctions.get(i));
		}
		return recents;
	}
	
	//private constructor to avoid client applications to use constructor
    private RecentCriteria(){}

    public static RecentCriteria getInstance(){
        return instance;
    }

}
