import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class SimParams {
	private String inputFileName = "input0.dat";
	private boolean isBatchRun = false;
	private boolean isWait = false;
	
	private int mediumDimensionX = 100;
	private int mediumDimensionY = 100;
	private int mediumDimensionZ = 100;
	private int maxSimulationStep = 25000000;
	private NanoMachineParam transmitter = null;
	private NanoMachineParam receiver = null;
	private HashSet<IntermediateNodeParam> intermediateNode = new HashSet<>();
	private int numMessages = 1;
	private int numRetransmissions = 0;
	private int retransmitWaitTime = 25000000;
	private double stepLengthX = 1.0;
	private double stepLengthY = 1.0;
	private double stepLengthZ = 1.0;
	private MoleculeParam infoMoleculeParam = null;
	private MoleculeParam ackMoleculeParam = null;
	private MoleculeParam noiseMoleculeParam = null;
	private int velRail = 1;
	private double probDRail = 0.0;
	private boolean useCollisions = false;
	private boolean useAcknowledgements = false;
	private boolean decomposing = false;
	private HashSet<MicrotubuleParam> microtubuleParams = new HashSet<>();
	private String outputFile = "output.txt";
	
	public SimParams(String[] args) {
		parseArgs(args);
		try {
			readParamFile();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void parseArgs(String[] args) {
		for(int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-pfile":
				inputFileName = args[++i];
				break;
			case "-batchRun":
				isBatchRun = true;
				break;
			case "-ptime":
				isWait = true;
				break;
			default:
				System.out.println("Error");
				System.exit(1);
			}
		}
	}
	
	private void readParamFile() throws Exception {
		String line;
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		while((line = br.readLine()) != null) {
			if(line.length() == 0 || line.charAt(0) == '*') {
				continue;
			}
			String paramName = line.split(" ")[0];
			String params = line.substring(line.indexOf(" ")+1).trim();
			
			switch(paramName) {
			case "mediumDimensionX":
				mediumDimensionX = Integer.parseInt(params);
				break;
			case "mediumDimensionY":
				mediumDimensionY = Integer.parseInt(params);
				break;
			case "mediumDimensionZ":
				mediumDimensionZ = Integer.parseInt(params);
				break;
			case "maxSimulationStep":
				maxSimulationStep = Integer.parseInt(params);
				break;
			case "transmitter":
				transmitter = new NanoMachineParam(params);
				break;
			case "receiver":
				receiver = new NanoMachineParam(params);
				break;
			case "intermediateNode":
				intermediateNode.add(new IntermediateNodeParam(params));
				break;
			case "numMessages":
				numMessages = Integer.parseInt(params);
				break;
			case "numRetransmissions":
				numRetransmissions = Integer.parseInt(params);
				break;
			case "retransmitWaitTime":
				retransmitWaitTime = Integer.parseInt(params);
				break;
			case "stepLengthX":
				stepLengthX = Double.parseDouble(params);
				break;
			case "stepLengthY":
				stepLengthY = Double.parseDouble(params);
				break;
			case "stepLengthZ":
				stepLengthZ = Double.parseDouble(params);
				break;
			case "moleculeParams":
				String[] ps = params.split(" ");
				switch(MoleculeType.getMoleculeType(ps[1])) {
				case INFO:
					infoMoleculeParam = new MoleculeParam(params);
					break;
				case ACK:
					ackMoleculeParam = new MoleculeParam(params);
					break;
				case NOISE:
					noiseMoleculeParam = new MoleculeParam(params);
					break;
				}
//				moleculeParams.add(new MoleculeParam(params));
				break;
			case "velRail":
				velRail = Integer.parseInt(params);
				break;
			case "probDRail":
				probDRail = Double.parseDouble(params);
				break;
			case "useCollisions":
				useCollisions = (Integer.parseInt(params) == 1) ? true : false;
				break;
			case "useAcknowledgements":
				useAcknowledgements = (Integer.parseInt(params) == 1) ? true : false;
				break;
			case "decomposing":
				decomposing = (Integer.parseInt(params) == 1) ? true : false;
				break;
			case "microtubuleParams":
				microtubuleParams.add(new MicrotubuleParam(params));
				break;
			case "outputFile":
				outputFile = params;
				break;
			default:
				break;
			}
		}
		br.close();
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public boolean isBatchRun() {
		return isBatchRun;
	}

	public boolean isWait() {
		return isWait;
	}

	public int getMediumDimensionX() {
		return mediumDimensionX;
	}

	public int getMediumDimensionY() {
		return mediumDimensionY;
	}

	public int getMediumDimensionZ() {
		return mediumDimensionZ;
	}

	public int getMaxSimulationStep() {
		return maxSimulationStep;
	}

	public NanoMachineParam getTransmitter() {
		return transmitter;
	}

	public NanoMachineParam getReceiver() {
		return receiver;
	}

	public HashSet<IntermediateNodeParam> getIntermediateNode() {
		return intermediateNode;
	}

	public int getNumMessages() {
		return numMessages;
	}

	public int getNumRetransmissions() {
		return numRetransmissions;
	}

	public int getRetransmitWaitTime() {
		return retransmitWaitTime;
	}

	public double getStepLengthX() {
		return stepLengthX;
	}

	public double getStepLengthY() {
		return stepLengthY;
	}

	public double getStepLengthZ() {
		return stepLengthZ;
	}

	public MoleculeParam getInfoMoleculeParam() {
		return infoMoleculeParam;
	}

	public MoleculeParam getAckMoleculeParam() {
		return ackMoleculeParam;
	}

	public MoleculeParam getNoiseMoleculeParam() {
		return noiseMoleculeParam;
	}

	public int getVelRail() {
		return velRail;
	}

	public double getProbDRail() {
		return probDRail;
	}

	public boolean isUseCollisions() {
		return useCollisions;
	}

	public boolean isUseAcknowledgements() {
		return useAcknowledgements;
	}

	public boolean isDecomposing() {
		return decomposing;
	}

	public HashSet<MicrotubuleParam> getMicrotubuleParams() {
		return microtubuleParams;
	}

	public String getOutputFile() {
		return outputFile;
	}
	
}
