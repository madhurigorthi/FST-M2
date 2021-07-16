package RestProject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GithubProject {
	
	RequestSpecification reqspec;
	String sshkey;
	int id;
	ArrayList<Integer> ids;
	
	@BeforeClass
	public void setup() {
		
		reqspec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri("https://api.github.com")
				.addHeader("Authorization", " ")
				.build();
		
	}
	
	@Test(priority=1)
	public void postsshkey() {
		String reqbody = " {\"title\": \"TestAPIKey\", \"key\": \"\"}";
		
		Response response = given().spec(reqspec).body(reqbody).when().post("/user/keys");
		
		System.out.println("response" + response.asPrettyString());
		
		id = response.then().extract().path("id");
		System.out.println("ID vlaue --"+id);		
		response.then().statusCode(201);
			
	}
	
	@Test(priority=2)	
	public void getsshkey() {
		
		Response response = given().spec(reqspec).when().get("/user/keys");
		System.out.println("Get response" + response.asPrettyString());		
		ids = response.then().extract().path("id");
		response.then().statusCode(200);
	}
	
	@Test(priority=3)	
	public void delsshkey() {
		
		for(int i=0;i<ids.size();i++) {
			
		Response response = given().spec(reqspec).pathParam("keyid",ids.get(i)).when().delete("/user/keys/{keyid}");		
		response.then().statusCode(204);
		
		}		
	}
	
	//@Test(priority=4)	
	public void validatedelsshkey() {
		
		Response response = given().spec(reqspec).when().get("/user/keys");
		System.out.println("Get response" + response.asPrettyString());
		response.then().statusCode(200);
	}
	

}
