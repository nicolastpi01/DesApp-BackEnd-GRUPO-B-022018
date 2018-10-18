package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY 
public class FinishCriteria {
	private static final int daysOfDifference = 3;

	//@Override
	//public List<Auction> search(List<Auction> auctions) {
	//	return auctions.stream().filter(auc -> auc.isNextToFinish(maxDaysRecent)).collect(Collectors.toList());
	//}
    
	
	/* 
	public ArrayList<Auction> apply(List<Auction> auctions) {
		ArrayList<Auction> nextToFinish = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if (auctions.get(i).isNextToFinish(daysOfDifference)) nextToFinish.add(auctions.get(i));
		}
		return nextToFinish;
	}
	
	*/

}
