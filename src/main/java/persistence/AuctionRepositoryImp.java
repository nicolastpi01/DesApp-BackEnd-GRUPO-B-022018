package persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import model.Auction;

@Repository
public class AuctionRepositoryImp implements AuctionRepositoryCustom {
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<Auction> findAuctionsByTitleAndAddress(String title, String address) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
 
        Root<Auction> book = cq.from(Auction.class);
        Predicate authorNamePredicate = cb.equal(book.get("address"), address);
        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
        cq.where(authorNamePredicate, titlePredicate);
 
        TypedQuery<Auction> query = em.createQuery(cq);
        return query.getResultList();
	}
	
	@Override
	public List<Auction> findAuctionsByOwner(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
 
        Root<Auction> auction = cq.from(Auction.class);
        Predicate predicate = cb.equal(auction.get("owner").get("id"), id);
        cq.where(predicate);
 
        TypedQuery<Auction> query = em.createQuery(cq);
        return query.getResultList();
	}
	
	

}
