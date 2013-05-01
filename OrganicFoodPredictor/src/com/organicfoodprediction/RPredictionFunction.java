package com.organicfoodprediction;
import java.io.IOException;
import rcaller.*;

/**
 * Uses RCaller to run R code for prediction 
 * @author group9
 *
 */
public class RPredictionFunction {

	//@SuppressWarnings("deprecation")

	RCaller caller;
	RCode code;
	public RPredictionFunction(RCaller caller,RCode code)
	{
		this.caller = caller;
		this.code = code;
	}
	/**
	 * 
	 * @param newFarm = new farm land for prediction
	 * @param newRain = new rain value for prediction
	 * @param newTemp = new Temperature value for prediction
	 * @param newIncome = new income value for prediction
	 * @return
	 */
	public String calculatePrediction(String newFarm,String newRain,String newTemp,String newIncome){
		
		
		code.addRCode("Farmland<-data$Farmland");
		code.addRCode("Farmland<-data$Farmland");
		code.addRCode("Rain<-data$Rain");
		code.addRCode("Temperature<-data$Temperature");
		code.addRCode("Sales<- data$Sales");
		code.addRCode("Year<-data$Year");
		code.addRCode("Income<-data$Income");
		code.addRCode("fit <- lm(Sales ~ Farmland + Rain + Temperature + Income)");
		code.addRCode("newfarm <-"+ newFarm);
		code.addRCode("newrain <-"+ newRain);
		code.addRCode("newtemp <-"+ newTemp);
		code.addRCode("newincome <-"+ newIncome);
		code.addRCode("newdata<-data.frame(Farmland=newfarm,Rain=newrain,Temperature=newtemp,Income=newincome)");
		code.addRCode("newsales<-predict(fit,newdata=newdata)");
		
		caller.setRCode(code);
		caller.runAndReturnResult("newsales");
		double[] result = caller.getParser().getAsDoubleArray("newsales");
		System.out.println("Predicted Organic Sales:"+result[0]);
		return Double.toString(result[0]);
		
	}
	
	public String farmTimeSeries() throws IOException{
		
		code.addRCode("fit <- arima(data$Farmland, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("fore$pred[1]");
		caller.setRCode(code);
		caller.runAndReturnResult("fore$pred[1]");
		double[] result = caller.getParser().getAsDoubleArray("fore$pred[1]");
		System.out.println("New Farm Land value:"+result[0]);
		return Double.toString(result[0]);
	}
	
	public String rainTimeSeries(){
		
		code.addRCode("fit <- arima(data$Rain, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("fore$pred[1]");
		caller.setRCode(code);
		caller.runAndReturnResult("fore$pred[1]");
		double[] result = caller.getParser().getAsDoubleArray("fore$pred[1]");
		System.out.println("New Rain Value"+result[0]);
		return Double.toString(result[0]);
	}
	
	public String temperatureTimeSeries(){

		code.addRCode("fit <- arima(data$Temperature, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("fore$pred[1]");
		caller.setRCode(code);
		caller.runAndReturnResult("fore$pred[1]");
		double[] result = caller.getParser().getAsDoubleArray("fore$pred[1]");
		System.out.println("New Temperature value:"+result[0]);
		return Double.toString(result[0]);
	}

	public String incomeTimeSeries(){

		code.addRCode("fit <- arima(data$Income, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("fore$pred[1]");
		caller.setRCode(code);
		caller.runAndReturnResult("fore$pred[1]");
		double[] result = caller.getParser().getAsDoubleArray("fore$pred[1]");
		System.out.println("New Incode Value:" + result[0]);
		return Double.toString(result[0]);
		

	}
	
}
