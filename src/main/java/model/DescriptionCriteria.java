
package model;

import java.util.List;
import java.util.stream.Collectors;

public class DescriptionCriteria implements SearchCriteria {
	private static final DescriptionCriteria instance = new DescriptionCriteria();
	private String description;
	
	@Override
	public List<Auction> search(List<Auction> auctions) {
		return auctions.stream().filter(sub -> description.contains(sub.getDescription())).collect(Collectors.toList());
	}
	
    //private constructor to avoid client applications to use constructor
    private DescriptionCriteria(){}

    public static DescriptionCriteria getInstance(String description){
    	instance.setDescription(description);
        return instance;
    }

	private void setDescription(String description) {
		this.description = description;
	}
	
}
