package model;

import java.util.ArrayList;
import java.util.List;
import model.exceptions.YouCanNotMakeAnOfferAboutAnAuctionWhereYouWentTheLastBiddingException;

import model.exceptions.YouCanNotHaveMoreThanFiveAuctionsInProgressException;
import model.exceptions.YouCanNotBidOnAnAuctionYouOwnException;

public class System {
	
	Registry registry;
	//Home home;
	List<Auction> auctions;
	ArrayList<User> users;
	
	public System() {
		auctions = new ArrayList<Auction>();
		users = new ArrayList<User>();
		registry = new Registry(users); // Se encarga de registro, verificar ingresos, etc
		//home = new Home(); // Se encarga de aplicar filtros sobre las subastas y mostrarlos ordenados
	}

	/* 
	public void create(Auction auction, User user) {
		if (canCreateAnAuction(user)) add(auction, user);
	}
	
	public void makeOffer(Auction auction, User user) {
		if (isAuthenticated(user)) {
			if (auction.canBid(user)) user.bid(auction);
				else if (auction.isInProgressFor(user))  
					throw new YouCanNotBidOnAnAuctionYouOwnException();
				else if (auction.isBidLast(user))  
					throw new YouCanNotMakeAnOfferAboutAnAuctionWhereYouWentTheLastBiddingException();
		}
		else { }
		// Excepciones de no autenticado...No Logueado, usuario inexistente, etc
	}
	

	
	private void add(Auction auction, User user) {
		auction.setState(new NewSubasta());
		auction.setOwner(user);
		auctions.add(auction);
	}
	
	

	private Boolean canCreateAnAuction(User user) {
		return isAuthenticated(user) && hasLessAuctionsInProgressThanTheMaximumAllowedAmount(user);
	}
	
	*/
	
	// es editar?
	//public void Modify(Auction auction, User user) {
	//	if (canEditAnAuction(auction, user)) edit(auction);
	//}
	
	//public void delete(Auction auction, User user) {
	//	if (canEditAnAuction(auction, user)) del(auction);
	//}
	
	// Las Excepciones van en este metodo, no en los internos
	//private Boolean canEditAnAuction(Auction auction, User user) {
	//	return isAuthenticated(user) && auction.canBeModified(user);
	//}
	
	// mmm, como hacerlo?
	private void edit(Auction auction) {
		/// ..................
	}
	
	private void del(Auction auction) {
		auctions.remove(auction);
	}

	///////////////////////////////////////// REGISTRO
	///////////////////////////////////////// ///////////////////////////////////////////////////////////////

	// ya inicio sesión?
	private Boolean isAuthenticated(User user) {
		return registry.isLogIn(user); // Dispara las distintas excepciones de porque este metodo podria fallar
		// UsuarioDebeIniciarSesionException(), UsuarioInexistenteException, etc.
	}

	// Autenticarse
	// Con cuenta de Gmail o Usuario
	public void logIn() {
	}

	//public void sigIn(User user) {
	//	if (registry.canSigIn(user)) {
	//		registry.sigIn(user);
	//		this.add(user);
	//	}
	//}

	////////////////////////////////////// FIND AUCTIONS
	////////////////////////////////////// /////////////////////////////////////////////////////

	/* 
	public ArrayList<Auction> progressAuctions() {
		ArrayList<Auction> inProgress = new ArrayList<Auction>();
		for (int i = 0; i < auctions.size(); i++) {
			if (auctions.get(i).isInProgress())
				inProgress.add(auctions.get(i));
		}
		return inProgress;
	}
	
	
	*/


	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// private methods

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public ArrayList<User> getUsers() {
		return this.users;
	}

	/*
	private void add(User user) {
		users.add(user);
		user.setProfile(new Registered()); // Para que estoy haciendo esto si despues no lo uso?
	}

	 
	private ArrayList<Auction> auctionsInProgress(User user) {
		ArrayList<Auction> inProgress = new ArrayList<Auction>();
		for (int i = 0; i < auctions.size(); i++) {
			if (auctions.get(i).isInProgressFor(user)) inProgress.add(auctions.get(i));
		}
		return inProgress;
	}
	
	
	// Revisar este, se puede expresar de otra manera para que quede mejor la
	// excepcion
	private Boolean hasLessAuctionsInProgressThanTheMaximumAllowedAmount(User user) {
		if (auctionsInProgress(user).size() < 5)
			return true;
		else
			throw new YouCanNotHaveMoreThanFiveAuctionsInProgressException();
	}
	
	
	
	public ArrayList<Auction> auctionsInWhichParticipated(User user) {
		ArrayList<Auction> auctionsWhereUserIsBidder = new ArrayList<Auction>();
		for(int i=0; i < auctions.size(); i++) {
			if(auctions.get(i).hasABidder(user)) auctionsWhereUserIsBidder.add(auctions.get(i));
		}
		return auctionsWhereUserIsBidder;
	}
	
	*/

}
