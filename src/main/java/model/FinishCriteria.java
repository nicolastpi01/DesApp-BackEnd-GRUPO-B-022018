package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY 
public class FinishCriteria {
	private static final int daysOfDifferenceAllowed = 3;

	//@Override
	//public List<Auction> search(List<Auction> auctions) {
	//	return auctions.stream().filter(auc -> auc.isNextToFinish(maxDaysRecent)).collect(Collectors.toList());
	//}
     
	public ArrayList<Auction> apply(List<Auction> auctions) {
		ArrayList<Auction> results = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if (auctions.get(i).isNextToFinish(daysOfDifferenceAllowed)) results.add(auctions.get(i));
		}
		return results;
	}
	
	

}
