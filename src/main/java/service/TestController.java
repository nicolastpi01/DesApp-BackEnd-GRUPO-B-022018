package service;

import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class TestController {
	//"Una pequeña clase para testear la seguridad de los end points"

	  @Value("${security.oauth2.resource.id}")
	  private String resourceId;

	  @Value("${auth0.domain}")
	  private String domain;

	 // @Value("${auth0.clientId}")
	 // private String clientId;

	@RequestMapping(value = "/public", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String publicEndpoint() {
		return new JSONObject()
				.put("message", "Hello from a public endpoint! You don\'t need to be authenticated to see this.")
				.toString();
	}

	@RequestMapping(value = "/private", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String privateEndpoint() {
		return new JSONObject()
				.put("message", "Hello from a private endpoint! You need to be authenticated to see this.").toString();
	}

	@RequestMapping(value = "/private-scoped", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String privateScopedEndpoint() {
		return new JSONObject().put("message",
				"Hello from a private endpoint! You need to be authenticated and have a scope of read:messages to see this.")
				.toString();
	}

	/*
	@RequestMapping(value = "/config", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAppConfigs() {
		return new JSONObject().put("domain", domain).put("clientID", clientId).put("audience", resourceId).toString();
	}
	*/
}
