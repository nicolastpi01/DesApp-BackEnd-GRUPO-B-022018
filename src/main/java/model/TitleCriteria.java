package model;

import java.util.ArrayList;
import java.util.List;

public class TitleCriteria implements SearchCriteria {
	private static final TitleCriteria instance = new TitleCriteria();
	String title;

	@Override
	public ArrayList<Auction> search(List<Auction> auctions) {
		ArrayList<Auction> auctionsResult = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if(auctions.get(i).hasASameTitle(title)) auctionsResult.add(auctions.get(i));	
		}
		return auctionsResult;
	}
	
    //private constructor to avoid client applications to use constructor
    private TitleCriteria(){}

    public static TitleCriteria getInstance(String title){
    	instance.setTitle(title);
        return instance;
    }

	private void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

}
