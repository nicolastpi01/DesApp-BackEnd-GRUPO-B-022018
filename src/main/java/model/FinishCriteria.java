package model;

import java.util.ArrayList;
import java.util.List;

//LA SUBASTA DEBE ESTAR EN PROGRESO Y COMPRENDIDA ENTRE LOS 3 DIAS ANTERIORES A HOY 
public class FinishCriteria {
	private static final int daysOfDifferenceAllowed = 3;

     
	public ArrayList<Auction> apply(List<Auction> auctions) {
		ArrayList<Auction> results = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if (auctions.get(i).isNextToFinish(daysOfDifferenceAllowed)) results.add(auctions.get(i));
		}
		return results;
	}
	
	

}
