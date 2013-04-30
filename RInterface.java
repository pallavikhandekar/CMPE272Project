package rcaller;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import rcaller.RCaller;
public class RInterface {

	@SuppressWarnings("deprecation")

	public String calculatePrediction(){
	
		RCaller caller = new RCaller();
		RCode code = new RCode();	
		
		caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		code.clear();
		code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.addRCode("Farmland<-data$Farmland");
		code.addRCode("Farmland<-data$Farmland");
		code.addRCode("Rain<-data$Rain");
		code.addRCode("Temperature<-data$Temperature");
		code.addRCode("Sales<- data$Sales");
		code.addRCode("Income<-data$Income");
		code.addRCode("fit <- lm(Sales ~ Farmland + Rain + Temperature + Income)");
		code.addRCode("newfarm<-x");
		code.addRCode("newrain<-y");
		code.addRCode("newtemp<-z");
		code.addRCode("newincome<-w");
		code.addRCode("newdata<-data.frame(Farmland=newfarm,Rain=newrain,Temperature=newtemp,Income=newincome)");
		code.addRCode("newsales<-predict(fit,newdata=newdata)");
		code.addRCode("result<-return (totalsales)");
		caller.setRCode(code);
		caller.runAndReturnResult("result");
		double[] result = caller.getParser().getAsDoubleArray("result");
		System.out.println(result[0]);
		return Double.toString(result[0]);
		
	}
	
	public String farmTimeSeries(){
		
		RCaller caller = new RCaller();
		RCode code = new RCode();	
		caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		code.clear();
		code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.addRCode("fit <- arima(data$Farmland, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("result <- return (fore$pred)");
		caller.setRCode(code);
		caller.runAndReturnResult("result");
		double[] result = caller.getParser().getAsDoubleArray("result");
		System.out.println(result[0]);
		return Double.toString(result[0]);

	}
	
	public String rainTimeSeries(){
		
		RCaller caller = new RCaller();
		RCode code = new RCode();	
		caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		code.clear();
		code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.addRCode("fit <- arima(data$Rain, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("result <- return (fore$pred)");
		caller.setRCode(code);
		caller.runAndReturnResult("result");
		double[] result = caller.getParser().getAsDoubleArray("result");
		System.out.println(result[0]);
		return Double.toString(result[0]);
	}
	
	public String temperatureTimeSeries(){

		RCaller caller = new RCaller();
		RCode code = new RCode();	
		caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		code.clear();
		code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.addRCode("fit <- arima(data$Temperature, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("result <- return (fore$pred)");
		caller.setRCode(code);
		caller.runAndReturnResult("result");
		double[] result = caller.getParser().getAsDoubleArray("result");
		System.out.println(result[0]);
		return Double.toString(result[0]);
	}

	public String incomeTimeSeries(){

		RCaller caller = new RCaller();
		RCode code = new RCode();
		caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		code.clear();
		code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.addRCode("fit <- arima(data$Income, order=c(1,0,0), list(order=c(2,1,0), period=1))");
		code.addRCode("fore <- predict(fit, n.ahead=1)");
		code.addRCode("result <- return (fore$pred)");
		caller.setRCode(code);
		caller.runAndReturnResult("result");
		double[] result = caller.getParser().getAsDoubleArray("result");
		System.out.println(result[0]);
		return Double.toString(result[0]);
		

	}
}
