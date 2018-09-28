package model;

import java.util.ArrayList;
import java.util.List;

public class Home {
	
	public List<Auction> subastasRecientes(List<Auction> auctions) {
		return RecentCriteria.getInstance().search(auctions);
	}

	public List<Auction> subastasPorTerminar(List<Auction> auctions) {
		return FinishCriteria.getInstance().search(auctions);
	}

	public List<Auction> buscarPorDescripcion(String description, ArrayList<Auction> auctions) {
		return DescriptionCriteria.getInstance(description).search(auctions);
	}
	
	public List<Auction> buscarPorTitulo(String title, ArrayList<Auction> auctions) {
		return TitleCriteria.getInstance(title).search(auctions);
	}

	public List<Auction> subastasPopulares(List<Auction> auctions) {
		return PopularCriteria.getInstance().search(auctions);
	}
	
}
