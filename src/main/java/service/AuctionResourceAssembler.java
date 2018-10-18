package service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import model.Auction;

@Component
public class AuctionResourceAssembler implements ResourceAssembler<Auction, Resource<Auction>> {

	@Override
	public Resource<Auction> toResource(Auction auction) {
		
		return new Resource<>(auction,
			linkTo(methodOn(AuctionController.class).one(auction.getId())).withSelfRel(),
			linkTo(methodOn(AuctionController.class).all()).withRel("auctions"));
	}
}
