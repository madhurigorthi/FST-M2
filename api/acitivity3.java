package Activities;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class acitivity3 {

	RequestSpecification reqspec;
	ResponseSpecification  resspec;
	
	 
	@BeforeClass
	
	public void setup() {
		
	 reqspec = new RequestSpecBuilder()
			             .setBaseUri("https://petstore.swagger.io/v2/pet")
			             .setContentType(ContentType.JSON)
			             .build();
	
	resspec = new ResponseSpecBuilder()
			                         .expectStatusCode(200)
			                         .expectContentType("application/json")
			                         .expectBody("status", equalTo("alive"))
			                         .build();
			 
	
	}
	
	@DataProvider	
	public Object[][] petInfoProvider() {
        // Setting parameters to pass to test case
        Object[][] testData = new Object[][] { 
            { 77232, "Riley", "alive" }, 
            { 77233, "Hansel", "alive" }
        };
        return testData;
    }
	
	@Test(dataProvider = "petInfoProvider",priority=1)
	public void insertpet(int id,String name , String status) {
		
		String reqbody= "{\"id\": "+id+", \"name\": \""+name+"\", \"status\": \""+status+"\"}";
		
		System.out.println("req body"+reqbody);
		
		Response response = given()
				            .spec(reqspec)
				            .body(reqbody)
				            .when().post();
		
		response.then().spec(resspec);
		
		      
				
	}
		
	@Test(dataProvider = "petInfoProvider",priority=2)
	public void getpetdetails(int id,String name , String status)
	{
		Response response = given().spec(reqspec) // Use requestSpec
                .pathParam("petId", id) // Add path parameter
                .when().get("/{petId}");
		
		response.then().spec(resspec).body("name",equalTo(name));
	}
	
	@Test(dataProvider = "petInfoProvider",priority=3)
	public void deletepetdetails(int id,String name , String status)
	{
		Response response = given().spec(reqspec) // Use requestSpec
                .pathParam("petId", id) // Add path parameter
                .when().delete("/{petId}");
		
		response.then().body("code",equalTo(200));
	}
	
	
	
	
			             
	
}
