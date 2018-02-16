import java.util.HashSet;

public class Medium {
	private int X;
	private int Y;
	private int Z;
	private MolComSim sim;
//	private MoleculeCreator nc; // NoiseMolecule
	private MovingManager mm;
	private HashSet<Molecule> movingMolecules;
	private HashSet<Molecule> molecules;
	private HashSet<NoiseMolecule> noiseMolecules;
	private MediumMap map;
	private NanoMachine transmitter;
	private NanoMachine receiver;
	
	public Medium(SimParams params, MolComSim sim) {
		X = params.getMediumDimensionX();
		Y = params.getMediumDimensionY();
		Z = params.getMediumDimensionZ();
		this.sim = sim;
		molecules = new HashSet<Molecule>();
		map = new MediumMap(params);
		movingMolecules = new HashSet<Molecule>();
	}
	
	public void init() {
		// Noise
		createNoiseMolecules();
		
		// Microtubule
		// とりあえず放置
		
		// NanoMachine
		transmitter = new Transmitter(sim.getSimParams().getTransmitter(), sim.getSimParams().getInfoMoleculeParam());
		map.add(transmitter);
		receiver = new Receiver(sim.getSimParams().getReceiver(), sim.getSimParams().getAckMoleculeParam());
		map.add(receiver);
		
		// Molecule
		createMolecules(transmitter);
	}
	
	public void addMolecule(Molecule m) {
		movingMolecules.add(m);
		molecules.add(m);
		map.add(m);
	}
	
	public void addNoiseMolecule(NoiseMolecule nm) {
		noiseMolecules.add(nm);
		molecules.add(nm);
		map.add(nm);
	}
	
	public void createNoiseMolecules() {
		MoleculeParam nmp = sim.getSimParams().getNoiseMoleculeParam();
		for(int i = 0; i < nmp.getNumMolecule(); i++) {
			int x = (int)((Math.random() * X) - (X / 2));
			int y = (int)((Math.random() * Y) - (Z / 2));
			int z = (int)((Math.random() * Y) - (Z / 2));
			NoiseMolecule nm = new NoiseMolecule(new Position(x, y, z), nmp);
			addMolecule(nm);
		}
	}
	
	public void createMolecules(NanoMachine nm) {
		for(int i = 0; i < nm.getNumMolecule(); i++) {
			Molecule m = nm.createMolecule();
			addMolecule(m);
		}
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getZ() {
		return Z;
	}
	
	
}
