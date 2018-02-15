import java.io.FileWriter;
import java.io.IOException;

public class MolComSim {
	
	private static MolComSim sim;
	private int step;
	private boolean lastMsgCompleted;
	private SimParams params;
	private FileWriter outputFile;
	private Medium medium;

	public static void main(String[] args) throws IOException {
		MolComSim sim = createInstance();
		sim.startSim(args);
	}
	
	private void startSim(String[] args) throws IOException {
		step = 0;
		lastMsgCompleted = false;
		params = new SimParams(args);
		outputFile = new FileWriter(params.getOutputFile());
		createMedium();
	}
	
	private static MolComSim createInstance() {
		if(sim != null) {
			return sim;
		}
		return new MolComSim();
	}
	
	private void createMedium() {
		medium = new Medium(params, this);
		medium.init();
	}
	
	public SimParams getSimParams() {
		return params;
	}

}
