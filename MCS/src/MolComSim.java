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
		long start = System.currentTimeMillis();
		MolComSim sim = createInstance();
		sim.startSim(args);
		System.out.println(String.format("Finish: %d ms", System.currentTimeMillis() - start));
	}
	
	private void startSim(String[] args) throws IOException {
		step = 0;
		lastMsgCompleted = false;
		params = new SimParams(args);
		outputFile = new FileWriter(params.getOutputFile());
		createMedium();
		run();
	}
	
	private void run() {
		for(; medium.getMolecules().size() != 0; step++) {
			if(step % 10000 == 9999) {
				System.out.println(step + 1 + " : " + medium.getMolecules().size());
			}
			lastMsgCompleted = medium.nextStep();
			if(!params.isWait() && lastMsgCompleted) {
				break;
			}
		}
		finishSim();
	}
	
	private void finishSim() {
		System.out.println("Finish: " + step);
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
