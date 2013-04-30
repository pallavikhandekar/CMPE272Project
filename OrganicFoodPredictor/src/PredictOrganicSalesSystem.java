import java.io.IOException;

/**
 * Calculates the prediction for 2013
 * @author Group9
 *
 */
public class PredictOrganicSalesSystem {	
	
	public static void main(String[] args) {
		RPredictionFunction objRPredictionFunction =
				new RPredictionFunction();
		
		try {
			objRPredictionFunction.calculatePrediction(objRPredictionFunction.farmTimeSeries(),
					objRPredictionFunction.rainTimeSeries(),objRPredictionFunction.temperatureTimeSeries(),
					objRPredictionFunction.incomeTimeSeries());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
