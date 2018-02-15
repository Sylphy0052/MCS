import java.util.ArrayList;

public class Medium {
	private int X;
	private int Y;
	private int Z;
	private MolComSim sim;
	private MoleculeCreator nc; // NoiseMolecule
	private MovingManager mm;
	private ArrayList<Molecule> molecules;
	private MediumMap map;
	
	public Medium(SimParams params, MolComSim sim) {
		X = params.getMediumDimensionX();
		Y = params.getMediumDimensionY();
		Z = params.getMediumDimensionZ();
		this.sim = sim;
		molecules = new ArrayList<Molecule>();
		map = new MediumMap(params);
	}
	
	public void init() {
		// Noise
		nc = new NoiseMoleculeCreator(sim.getSimParams(), this);
		nc.createMolecule();
		
		// Microtubule
		
		
		// NanoMachine
		
		
	}
}
