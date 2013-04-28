
import java.io.File;
import java.io.IOException;

import rcaller.RCaller;


public class PredictOrganicSalesController {	
	
	public static void main(String[] args) {
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
		caller.showPlot(file);
	}
	
}
