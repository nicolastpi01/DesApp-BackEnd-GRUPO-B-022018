package persistenceTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import model.Auction;
import service.AuctionRepository;
import service.AuctionSpecifications;

import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { PersistenceJPAConfig.class })
//@Transactional
//@TransactionConfiguration
public class JpaSpecificationsTest {
	/*	
	@Autowired
    private AuctionRepository repository;
    private Auction chaquetaRichie;
    private Auction mascaraCorradoS;
 
    
    @Before
    public void init() {
        chaquetaRichie = new Auction();
        chaquetaRichie.setTitle("chaqueta de Richie Aprile");
        chaquetaRichie.setAddress("Avenida Bergenline");
        repository.save(chaquetaRichie);
 
        //userTom = new User();
        //userTom.setFirstName("Tom");
        //userTom.setLastName("Doe");
        //userTom.setEmail("tom@doe.com");
        //userTom.setAge(26);
        //repository.save(userTom);
    }
    
    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        
    	List<Auction> results = repository.findAll(AuctionSpecifications.hasTitle("chaqueta de Richie Aprile")); 
     
        assertThat(chaquetaRichie, isIn(results));
    }
    
	*/
}
