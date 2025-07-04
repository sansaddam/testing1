package Practice;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;

import static io.restassured.RestAssured.given;

public class FlowableDeplomentHello {

	    public static void main(String[] args) {
	        // Base URI
	        RestAssured.baseURI = "http://localhost:8080/flowable-rest";

	        // File to upload
	        File bpmnFile = new File("../Camunda/src/test/java/hello.bpmn20.xml");

	        // Make POST call to deployment endpoint
	        Response response = given()
	            .auth().preemptive().basic("rest-admin", "test")
	            .multiPart("file", bpmnFile, "application/xml")
	            .formParam("deploymentName", "MyDeployment")
	            .post("/service/repository/deployments");

	        // Validate and print response
	        if (response.statusCode() == 201) {
	            System.out.println("✅ Deployment successful:");
	            System.out.println(response.asPrettyString());
	        } else {
	            System.out.println("❌ Deployment failed. Status: " + response.statusCode());
	            System.out.println(response.asPrettyString());
	        }
	    }
	

}
