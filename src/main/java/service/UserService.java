package service;


import java.util.Set;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.Auction;
import model.User;
import model.UserValidation;
import model.exceptions.InvalidEmailException;
import model.exceptions.UserNotFoundException;

@Service
public class UserService {
	private final UserRepository repository;
	private final UserValidation validation;
	
	 @Value("${security.oauth2.resource.id}")
	  private String resourceId;

	  @Value("${auth0.domain}")
	  private String domain;

	  @Value("${auth0.clientId}")
	  private String clientId;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
		this.validation = new UserValidation();
	}
	
	// logIn logOut
	
	// todas las subastas de un usuario por id
	public Set<Auction> searchUserAuctionsById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
			return user.getAuctionsThatIOwn();
	}
	
	// todas las subastas donde pujo un usuario por id
	public Set<Auction> searchBidderAuctionsForAUserById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
			return user.getAuctionsInWhichIBid();
	}

	// create a newUser
	public void sigIn(User newUser) {
		this.validation.validate(newUser);
		repository.save(newUser);
	}

	// update a existing user
	public User updateUser(Long id, User newUser) {
		this.validation.validate(newUser);
		return repository.findById(id)
			.map(user -> {
				user.setName(newUser.getName());
				user.setLastName(newUser.getLastName());
				user.setUserName(newUser.getUserName());
				user.setBirthday(newUser.getBirthday());
				user.setEmail(newUser.getEmail());
				user.setPassword(newUser.getPassword());
				return repository.save(user);
			})
			.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	
	public User getOne(Long id) {
		User user = repository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}

	// No podes tener una subasta donde esten pujando otros usuarios
	// Si estabas pujando sobre una subasta automaticamente la perdes, y se la lleva el anterior postor
	public void delete(Long id) {
		repository.findById(id)
		.orElseThrow(() -> new UserNotFoundException(id));
			repository.deleteById(id);		
	}

	public User logIn(String email) throws UnirestException {
		User user = repository.findByEmail(email)
		.orElseThrow(() -> new InvalidEmailException(email));
		HttpResponse<String> response = Unirest.post("https://example-secure-api.auth0.com/oauth/token")
				  .header("content-type", "application/json")
				  .body("{\"client_id\":\"JLqj7ZP6wWR2KynpqWF979ojFoPQRRSV\",\"client_secret\":\"LieqwJsnORu3-5ZWn0Lg_LskCqECVcC__vxcrVRckzd_vlrdozSgSTHuNxge8wZ0\",\"audience\":\"http://localhost:8080\",\"grant_type\":\"client_credentials\"}")
				  .asString();
		
		JSONObject obj = new JSONObject(response.getBody());
		//String pageName = obj.getJSONObject("pageInfo").getString("pageName");
		String accessToken = obj.getString("access_token");
		System.out.println(accessToken);
		/* old at
		 * eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik1qUkZNVVV4TVRreVF6Y3dOREU1UVVJNE5UZEZSRVZCT1VSR05UUkZPVVF3UkRSQ1JEUTNOdyJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUtc2VjdXJlLWFwaS5hdXRoMC5jb20vIiwic3ViIjoiSkxxajdaUDZ3V1IyS3lucHFXRjk3OW9qRm9QUVJSU1ZAY2xpZW50cyIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCIsImlhdCI6MTU0MzUyNTg4NiwiZXhwIjoxNTQzNjEyMjg2LCJhenAiOiJKTHFqN1pQNndXUjJLeW5wcVdGOTc5b2pGb1BRUlJTViIsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyJ9.O8qBnpEbK8EIGPH7MFTFuP7kDasw7fIsb0q7-2vx17nozRyDlAqlN1nj__5fkBQtCjLFs9ja0J1ADXmwi8F5lxQiELED7UiXPxgtJbJ2EOQxHgvPo1cJASJjlkYokLX1muFswNJ980vLjZGCSvWsRj0fL6vvZQU8YJAWhzMdqA4s-4qPB2LdzAt3Im-aRsfE2xreluMPaKPrpKobR09o51CDecbnVXLikGSORORGid1rDy2evl3108QuNDACDLiJ0nGUb6M6YQSoLoia2qkcOaO0aXhEzVVhI1jGnAShELRBSTLwkc93H7lBSwehnSKNzCGrq1ZfOCyc8Ds5WFGuSA
		 */
		user.setAccessToken(accessToken);
		return user;
	}
	

}
