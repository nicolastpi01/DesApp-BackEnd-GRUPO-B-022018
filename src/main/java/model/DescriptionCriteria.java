
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DescriptionCriteria implements SearchCriteria {
	private static final DescriptionCriteria instance = new DescriptionCriteria();
	private String description;
	
	@Override
	public List<Auction> search(List<Auction> auctions) {
		/*ArrayList<Subasta> subastasConDesc = new ArrayList<Subasta>();
		for (int i=0; i < subastas.size(); i++) {
			if (subastas.get(i).tieneDescripcion(getDescripcion())) subastasConDesc.add(subastas.get(i));
		}
		return subastasConDesc;
		*/
		return auctions.stream().filter(sub -> sub.getDescription().contains(description)).collect(Collectors.toList());
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
