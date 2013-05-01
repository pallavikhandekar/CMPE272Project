package com.organicfoodprediction;
import javax.xml.ws.Endpoint;
public class Server {
	
	public static void main(String[] args) {
	Endpoint.publish("http://localhost:9898/PredictOrganicFoodSales", new PredictOrganicSalesSystem());
	System.out.println("PredictOrganicFoodSales service is ready");
	}
}