import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import rcaller.*;

/**
 * Calculates the prediction for 2013
 * @author Group9
 *
 */
public class PredictOrganicSalesSystem {	
	
	public static void main(String[] args) {
		String predictedSale="";
		String predictionYear= "2013";
		RCaller caller = new RCaller();
		RCode code = new RCode();
		//caller.setRscriptExecutable("C:\\Program Files\\R\\R-2.15.2\\bin\\Rscript.exe");
		//code.addRCode("data <- read.csv(\"C:\\Users\\Saurabh\\Downloads\\mastersheet.csv\")");
		code.clear();
		code.addRCode("data <- read.csv(\"/Users/palloabhi/Downloads/mastersheet.csv\")");
		caller.setRscriptExecutable("/usr/bin/Rscript");
		caller.cleanRCode();
		
		RPredictionFunction objRPredictionFunction =
				new RPredictionFunction(caller,code);
		
		try {
			predictedSale = objRPredictionFunction.calculatePrediction(objRPredictionFunction.farmTimeSeries(),
					objRPredictionFunction.rainTimeSeries(),objRPredictionFunction.temperatureTimeSeries(),
					objRPredictionFunction.incomeTimeSeries());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		//Fetch the Sales and Years array to plot.
		caller.runAndReturnResult("Sales");
		ArrayList<String> sales = new ArrayList<String>(Arrays.asList(caller.getParser().getAsStringArray("Sales")));
		sales.add(predictedSale);
		System.out.println(sales.size());
		caller.runAndReturnResult("Year");
		ArrayList<String> years = new ArrayList<String>(Arrays.asList(caller.getParser().getAsStringArray("Year")));
		years.add(predictionYear);
		System.out.println(years.size());
	
		
	}
	
/*	//TEST CODE
	double[] numbers = new double[]{1,4,3,5,6,10};
	RCaller caller = new RCaller();
	caller.setRscriptExecutable("/usr/bin/Rscript");
	caller.cleanRCode();
	caller.addDoubleArray("x", numbers);
	File file = null;
	try {
		file = caller.startPlot();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	caller.addRCode("plot.ts(x)");
	caller.endPlot();
	caller.runOnly();
	caller.showPlot(file);*/
}
